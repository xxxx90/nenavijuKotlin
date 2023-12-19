import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class NoteServiceTest {
    @Before
    fun setUp() {
        NoteService.clear()
    }

    @Test
    fun testGetNoteTrue() {
        val note1 = Note(date = 10)
        val note2 = Note(date = 20)
        val note3 = Note(date = 30)
        NoteService.add(note1)
        NoteService.add(note2)
        NoteService.add(note3)
        val rezult = NoteService.getById(0)?.date

        assertEquals(10, rezult)
    }
    @Test
    fun testGetNoteFalse() {
        val note1 = Note(date = 10)
        val note2 = Note(date = 20)
        val note3 = Note(date = 30)
        NoteService.add(note1)
        NoteService.add(note2)
        NoteService.add(note3)
        val rezult = NoteService.getById(5)?.date

        assertEquals(null, rezult)
    }

    @Test
    fun testAddNote() {
        val note1 = Note(date = 10)
        val note2 = Note(date = 20)
        val note3 = Note(date = 30)
        NoteService.add(note1)
        NoteService.add(note2)
        NoteService.add(note3)
        NoteService.add(note3)
        val rezult = NoteService.get().size
        assertEquals(4, rezult)
    }

    @Test
    fun testEditNote() {
        val note1 = Note(date = 10)
        val note2 = Note(date = 20)
        val note3 = Note(date = 30)
        NoteService.add(note1)
        NoteService.add(note2)

        val rezult = NoteService.edit(note3)
        assertTrue( rezult)
    }



}


class WallServiceTest {
    @Before
    fun setUp() {
        WallService.clear()
    }

    @Test
    fun testAdd() {
        val post1 = Post(
            comments = Comments(),
            copyright = " ",
            likes = Likes(),
            repost = Reposts(),
            text = "yes",
            views = null
        )
        WallService.add(post1)
        val result = (WallService.idCounter)


        assertEquals(2, result)
    }

    @Test
    fun testUpdateTrue() {
        //    WallService.clear()
        val post1 = Post(views = null)
        val post2 = Post(views = null)
        val post4 = Post(views = null)
        val post5 = Post(views = null)
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
        val result = WallService.createComment(2, Comment(text = "Test")).text
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