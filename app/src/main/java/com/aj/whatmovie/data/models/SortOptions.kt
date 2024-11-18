package com.aj.whatmovie.data.models

enum class SortOptions(val value: String) {
    ORIGINAL_TITLE_ASC("original_title.asc"),
    ORIGINAL_TITLE_DESC("original_title.desc"),
    POPULARITY_ASC("popularity.asc"),
    POPULARITY_DESC("popularity.desc"),
    REVENUE_ASC("revenue.asc"),
    REVENUE_DESC("revenue.desc"),
    PRIMARY_RELEASE_DATE_ASC("primary_release_date.asc"),
    TITLE_ASC("title.asc"),
    TITLE_DESC("title.desc"),
    PRIMARY_RELEASE_DATE_DESC("primary_release_date.desc"),
    VOTE_AVERAGE_ASC("vote_average.asc"),
    VOTE_AVERAGE_DESC("vote_average.desc"),
    VOTE_COUNT_ASC("vote_count.asc"),
    VOTE_COUNT_DESC("vote_count.desc");

    companion object {
        fun fromValue(value: String): SortOptions? = entries.find { it.value == value }
    }
}
