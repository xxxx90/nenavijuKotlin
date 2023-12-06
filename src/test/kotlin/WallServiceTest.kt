import org.junit.Test
import org.junit.Assert.*
import org.junit.Before





class WallServiceTest {
    @Before
    fun setUp() {
        WallService.clear()
    }
    @Test
    fun testAdd() {
        val post1 = Post(comments = Comments(), copyright = " ", likes = Likes(), repost = Reposts(), text = "yes", views = null)
        WallService.add(post1)
        val result = (WallService.idCounter)


        assertEquals(2, result)
    }

    @Test
    fun testUpdateTrue() {
        //    WallService.clear()
        val post1 = Post( views = null)
        val post2 = Post( views = null)
        val post4 = Post( views = null)
        val post5 = Post( views = null)
        val post3 = Post(id = 1, views = null)
        WallService.add(post1)
        WallService.add(post2)
        WallService.add(post3)
        WallService.printPost()
        println()
        val result = WallService.update(post3)
        WallService.printPost()
        assertTrue(result)

    }


    @Test
    fun testUpdateFalse() {
        //    WallService.clear()
        val post1 = Post(views = null)
        val post2 = Post(views = null)
        val post4 = Post(views = null)
        val post5 = Post(views = null)
        val post3 = Post(id = 8, views = null)
        WallService.add(post1)
        WallService.add(post2)
        WallService.add(post3)
        WallService.printPost()
        println()
        val result = WallService.update(post3)
        WallService.printPost()
        assertFalse(result)

    }
@Test
fun testAddCommentTrue() {

    val post1 = Post()
    val post2 = Post()
    val post3 = Post()
    val post4 = Post()
    WallService.add(post1)
    WallService.add(post2)
    WallService.add(post3)
    WallService.add(post4)
    val  result =WallService.createComment(2, Comment(text = "Test")).text
    assertEquals(result, "Test")
}
    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val post1 = Post()
        val post2 = Post()
        val post3 = Post()
        val post4 = Post()
        WallService.add(post1)
        WallService.add(post2)
        WallService.add(post3)
        WallService.add(post4)
        WallService.createComment(8, Comment(text = "Test")).text


    }



}