import java.lang.RuntimeException

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
    val views: View? = null
) {
}

object WallService {
    var idCounter = 1
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
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

    fun createComment(postId: Int, comment: Comment): Comment {

        for (postInArray in posts) {
            //println(postInArray)
            if (postInArray.id == postId) {
                comments += comment
                return comments.last()
            }
        }
throw  PostNotFoundException("No post wist $postId")

    }


}

class PostNotFoundException (message: String) :RuntimeException (message)
data class Donut (val is_donut: Boolean=false, val placeholder : String="")

class Comment (val id: Int=0, val from_id : Int=0, val date: Int =0, val text: String="", val donut:Donut?=null,
    val reply_to_user: Int=0, val reply_to_comment: Int=0, val attachments: Attachment?=null, )

interface Attachment {
    val type: String
}

class Photo(override val type: String = "photo", val photo: PhotoType) : Attachment
data class PhotoType(
    var id: Int,
    var album_id: Int,
    var owner_id: Int,
    var user_id: Int,
    var text: String,
)

class Video(override val type: String = "video", val video: VideoType) : Attachment
data class VideoType(
    var id: Int,
    var owner_id: Int,
    var title: String,
    var description: String,
    var duration: Int,
)

class File(override val type: String = "file", val fileType: FileType) : Attachment
data class FileType(
    var id: Int,
    var owner_id: Int,
    var title: String,
    var size: Int,
    var ext: String
)

class Present(override val type: String = "present", val present: PresentType) : Attachment
data class PresentType(
    var id: Int,
    var thumb_256: String,
    var thumb_96: String,
    var thumb_48: String
)

class Story(override val type: String = "story", val story: StoryType) : Attachment
data class StoryType(
    var id: Int,
    var owner_id: Int,
    var date: Int,
    var is_expired: Boolean,
    var is_deleted: Boolean,
    var photo: Photo,
    var videoType: Video
)
