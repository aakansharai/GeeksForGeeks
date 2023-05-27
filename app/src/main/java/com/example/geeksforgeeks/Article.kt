package com.example.geeksforgeeks

data class Article(
    val feed: Feed,
    val items: List<Item>,
    val status: String
)