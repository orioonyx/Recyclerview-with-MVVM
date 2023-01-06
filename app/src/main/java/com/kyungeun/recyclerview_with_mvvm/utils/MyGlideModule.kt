package com.kyungeun.recyclerview_with_mvvm.utils

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.ModelCache
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader
import com.bumptech.glide.module.AppGlideModule
import java.io.InputStream
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * A custom [AppGlideModule] that registers a custom [ModelLoader] to handle loading images from
 * [String] URLs that contain a width parameter.
 */
@GlideModule
class MyGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.replace(String::class.java, InputStream::class.java, VariableWidthModelFactory())
    }
}

class VariableWidthModelFactory : ModelLoaderFactory<String, InputStream> {
    override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<String, InputStream> {
        return VariableWidthImageLoader(
            multiFactory.build(
                GlideUrl::class.java,
                InputStream::class.java
            )
        )
    }

    override fun teardown() {
        // Do nothing.
    }
}

class VariableWidthImageLoader(concreteLoader: ModelLoader<GlideUrl?, InputStream?>?) :
    BaseGlideUrlLoader<String>(concreteLoader, ModelCache(500)) {

    override fun getUrl(model: String, width: Int, height: Int, options: Options?): String {
        // Check for a width parameter in the URL
        // url example : myserver.com/images/__w-200-400-600-800-1000__/session1.jpg
        var glideModel = model

        val m: Matcher = URL_WIDTH_PATTERN.matcher(glideModel)
        var bestBucket = 0
        if (m.find()) {
            val found = m.group(1)?.split("-")?.toTypedArray()
            val length = found?.size
            if (length != null) {
                if (length > 0) {
                    for (i in 0 until length) {
                        val bucket = found[i].toInt()
                        if (bucket >= width) {
                            bestBucket = bucket
                            break
                        }
                    }
                    if (bestBucket > 0) {
                        // Replace the width parameter with the best bucket
                        // url example : myserver.com/images/w200/session1.jpg
                        glideModel = m.replaceFirst("w$bestBucket")
                    }
                }
            }
        }
        return glideModel
    }

    override fun handles(s: String): Boolean {
        return true
    }

    companion object {
        val URL_WIDTH_PATTERN: Pattern = Pattern.compile("__w-((?:-?\\d+)+)__")
    }
}
