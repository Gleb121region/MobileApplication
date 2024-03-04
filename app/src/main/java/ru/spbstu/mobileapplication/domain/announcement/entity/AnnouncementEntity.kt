package ru.spbstu.mobileapplication.domain.announcement.entity

//{
//  "city": "string",
//  "underground": "string",
//  "district": "string",
//  "roomsCounts": [ 0 ],
//  "maxPricePerMonth": 100000000,
//  "minPricePerMonth": 0,
//  "isRefrigerator": true,
//  "isWashingMachine": true,
//  "isTV": true,
//  "isShowerCubicle": true,
//  "isBathtub": true,
//  "isFurnitureRoom": true,
//  "isFurnitureKitchen": true,
//  "isDishwasher": true,
//  "isAirConditioning": true,
//  "isInternet": true
//}

data class AnnouncementEntity(
    val city: String,
    val underground: String,
    val district: String,
    val roomsCounts: List<Int>,
    val maxPricePerMonth: Int,
    val minPricePerMonth: Int,
    val isRefrigerator: Boolean,
    val isWashingMachine: Boolean,
    val isTV: Boolean,
    val isShowerCubicle: Boolean,
    val isBathtub: Boolean,
    val isFurnitureRoom: Boolean,
    val isFurnitureKitchen: Boolean,
    val isDishwasher: Boolean,
    val isAirConditioning: Boolean,
    val isInternet: Boolean,
)
