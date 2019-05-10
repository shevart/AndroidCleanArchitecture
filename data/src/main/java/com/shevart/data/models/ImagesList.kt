package com.shevart.data.models

import com.shevart.domain.models.launch.Image

/**
 * Wrapper for [List]<[Image]>. Need for DI
 */
class ImagesList(val images: List<Image>)