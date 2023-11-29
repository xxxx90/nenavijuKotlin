fun main(args: Array<String>) {
    val comments = Comments()
    val likes = Likes()
    val report = Reposts()

    println(WallService.idCounter)

    val post1 = Post(comments = comments, likes = likes, repost = report, text = "post1", views = null)
    val post2 = Post(id = 2, comments = comments, likes = likes, repost = report, text = "post2", views = null)
    val post3 = Post(text = "post3", views = null)
    val post4 = Post(text = "post4", views = null)
    val post5 = Post(text = "post5", views = null)
    val post6 = Post(text = "post6", views = null)
    val post7 = Post(text = "post7", views = null)
    val post8 = Post(text = "post8", views = View())
    val post9 = Post(id = 5, text = "post9", views = View())
    WallService.add(post1)
    WallService.add(post2)
    WallService.add(post3)
    WallService.add(post4)
    WallService.add(post5)
    WallService.add(post6)
    WallService.add(post7)
    WallService.add(post8)
    WallService.add(post9)

    WallService.printPost()

    println(" ==================== ")
    WallService.update(post2)

    WallService.printPost()
}


data class Post(
    val id: Int = 0,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    val date: Int = 0,
    var text: String = "",
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendsOnly: Boolean = false,
    val comments: Comments = Comments(),
    val copyright: String = "",
    val likes: Likes = Likes(),
    val repost: Reposts = Reposts(),
    val views: View?
) {
}

object WallService {
    var idCounter = 1
    private var posts = emptyArray<Post>()
    private var id = 0

    fun add(post: Post): Post {
        posts += post.copy(id = idCounter++)



        return posts.last()
    }

    fun update(post: Post): Boolean {


        for ((index, postInArray) in posts.withIndex()) {
            //println(postInArray)
            if (postInArray.id == post.id) {
                posts[index] = post.copy(
                    ownerId = 1,
                    fromId = 2,
                    friendsOnly = true,
                    text = "update",
                    repost = post.repost.copy(count = 5, userReposted = true)
                )
                return true
            }
        }
        return false
    }


    fun printPost() {

        for (post in posts) {
            println(post)

        }
    }

    fun clear() {
        posts = emptyArray()
        idCounter = 1

    }

}
