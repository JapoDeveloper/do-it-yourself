package co.japo.doityourself.converters;

import co.japo.doityourself.commands.NotesCommand;
import co.japo.doityourself.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class NotesCommandToNotesTest {

    private final long ID = 1l;
    private final String NOTES = "Some text";
    private NotesCommandToNotes converter;

    @Before
    public void setUp(){
        this.converter = new NotesCommandToNotes();
    }

    @Test
    public void testNullObject(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert(){
        NotesCommand source = new NotesCommand();
        source.setId(ID);
        source.setNotes(NOTES);

        Notes result = converter.convert(source);

        assertEquals(ID,result.getId().longValue());
        assertEquals(NOTES, result.getNotes());
    }

}
