package co.japo.doityourself.converters;

import co.japo.doityourself.commands.NotesCommand;
import co.japo.doityourself.commands.UnitOfMeasureCommand;
import co.japo.doityourself.domain.Notes;
import co.japo.doityourself.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class NotesToNotesCommandTest {

    private final long ID = 1l;
    private final String NOTES = "Some text";
    private NotesToNotesCommand converter;

    @Before
    public void setUp(){
        this.converter = new NotesToNotesCommand();
    }

    @Test
    public void testNullObject(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert(){
        Notes source = new Notes();
        source.setId(ID);
        source.setNotes(NOTES);

        NotesCommand result = converter.convert(source);

        assertEquals(ID,result.getId().longValue());
        assertEquals(NOTES, result.getNotes());
    }

}
