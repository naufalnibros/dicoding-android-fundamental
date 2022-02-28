package com.naufalnibros.submission_fundamental.core.remote.response

import com.google.gson.annotations.SerializedName
import com.naufalnibros.submission_fundamental.repository.user.User
import io.reactivex.Observable


/**
 *
 * {
    "total_count": 2,
    "incomplete_results": false,
    "items": [
            {
            "login": "sidiqpermana",
            "id": 4090245,
            "node_id": "MDQ6VXNlcjQwOTAyNDU=",
            "avatar_url": "https://avatars.githubusercontent.com/u/4090245?v=4",
            "gravatar_id": "",
            "url": "https://api.github.com/users/sidiqpermana",
            "html_url": "https://github.com/sidiqpermana",
            "followers_url": "https://api.github.com/users/sidiqpermana/followers",
            "following_url": "https://api.github.com/users/sidiqpermana/following{/other_user}",
            "gists_url": "https://api.github.com/users/sidiqpermana/gists{/gist_id}",
            "starred_url": "https://api.github.com/users/sidiqpermana/starred{/owner}{/repo}",
            "subscriptions_url": "https://api.github.com/users/sidiqpermana/subscriptions",
            "organizations_url": "https://api.github.com/users/sidiqpermana/orgs",
            "repos_url": "https://api.github.com/users/sidiqpermana/repos",
            "events_url": "https://api.github.com/users/sidiqpermana/events{/privacy}",
            "received_events_url": "https://api.github.com/users/sidiqpermana/received_events",
            "type": "User",
            "site_admin": false,
            "score": 1
        },
        {
            "login": "sidiqpermana",
            "id": 4090245,
            "node_id": "MDQ6VXNlcjQwOTAyNDU=",
            "avatar_url": "https://avatars.githubusercontent.com/u/4090245?v=4",
            "gravatar_id": "",
            "url": "https://api.github.com/users/sidiqpermana",
            "html_url": "https://github.com/sidiqpermana",
            "followers_url": "https://api.github.com/users/sidiqpermana/followers",
            "following_url": "https://api.github.com/users/sidiqpermana/following{/other_user}",
            "gists_url": "https://api.github.com/users/sidiqpermana/gists{/gist_id}",
            "starred_url": "https://api.github.com/users/sidiqpermana/starred{/owner}{/repo}",
            "subscriptions_url": "https://api.github.com/users/sidiqpermana/subscriptions",
            "organizations_url": "https://api.github.com/users/sidiqpermana/orgs",
            "repos_url": "https://api.github.com/users/sidiqpermana/repos",
            "events_url": "https://api.github.com/users/sidiqpermana/events{/privacy}",
            "received_events_url": "https://api.github.com/users/sidiqpermana/received_events",
            "type": "User",
            "site_admin": false,
            "score": 1
        }
    ]
}
 *
 */
data class UserSearchResponse(
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,

    @SerializedName("items")
    val items: List<User> = emptyList()
)

fun UserSearchResponse.mapToList() = Observable.just(this.items)