package SoftwareTesting.GatheringRequirements;

import SoftwareTesting.TestDrivenDevelopment.GatheringRequirements.CompactDisc;
import SoftwareTesting.TestDrivenDevelopment.GatheringRequirements.Library;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchCDTest {

    @Test
    public void oneMatchInCatalogue() {
        CompactDisc highwayToNowhere = new CompactDisc("Highway to Nowhere", "Drake Bell");
        Library library = new Library();

        HashMap<CompactDisc, Integer> newStock = new HashMap<>();
        newStock.put(highwayToNowhere, 10);
        library.receiveCDStock(newStock);

        HashMap<CompactDisc, Integer> searchResults = new HashMap<>();
        searchResults.put(highwayToNowhere, 10);
        assertEquals(searchResults, library.searchCatalogue("Highway to Nowhere", "Drake Bell"));
    }

    @Test
    public void noMatchesInCatalogue() {
        CompactDisc highwayToNowhere = new CompactDisc("Highway to Nowhere", "Drake Bell");
        Library library = new Library();

        HashMap<CompactDisc, Integer> newStock = new HashMap<>();
        newStock.put(highwayToNowhere, 10);
        library.receiveCDStock(newStock);

        HashMap<CompactDisc, Integer> searchResults = new HashMap<>();
        assertEquals(searchResults, library.searchCatalogue("La Da Dee", "Cody Simpson"));
    }

    @Test
    public void multipleMatchesInCatalogueWhenMatchOnCDNameOnly() {
        CompactDisc highwayToNowhere = new CompactDisc("Highway to Nowhere", "Drake Bell");
        CompactDisc soulManDrake = new CompactDisc("Soul Man", "Drake Bell");
        CompactDisc soulManCody = new CompactDisc("Soul Man", "Cody Simpson");
        Library library = new Library();

        HashMap<CompactDisc, Integer> newStock = new HashMap<>();
        newStock.put(highwayToNowhere, 10);
        newStock.put(soulManDrake, 10);
        newStock.put(soulManCody, 10);
        library.receiveCDStock(newStock);

        HashMap<CompactDisc, Integer> searchResults = new HashMap<>();
        searchResults.put(soulManDrake, 10);
        searchResults.put(soulManCody, 10);
        assertEquals(searchResults, library.searchCatalogue("Soul Man", null));
    }

    @Test
    public void multipleMatchesInCatalogueWhenMatchOnCDArtistOnly() {
        CompactDisc highwayToNowhere = new CompactDisc("Highway to Nowhere", "Drake Bell");
        CompactDisc soulMan = new CompactDisc("Soul Man", "Drake Bell");
        Library library = new Library();

        HashMap<CompactDisc, Integer> newStock = new HashMap<>();
        newStock.put(highwayToNowhere, 10);
        newStock.put(soulMan, 10);
        library.receiveCDStock(newStock);

        HashMap<CompactDisc, Integer> searchResults = new HashMap<>();
        searchResults.put(highwayToNowhere, 10);
        searchResults.put(soulMan, 10);
        assertEquals(searchResults, library.searchCatalogue(null, "Drake Bell"));
    }

    @Test
    public void allMatchesInCatalogueWhenMatchOnNothing() {
        CompactDisc highwayToNowhere = new CompactDisc("Highway to Nowhere", "Drake Bell");
        CompactDisc soulManDrake = new CompactDisc("Soul Man", "Drake Bell");
        CompactDisc soulManCody = new CompactDisc("Soul Man", "Cody Simpson");
        CompactDisc laDaDee = new CompactDisc("La Da Dee", "Cody Simpson");
        Library library = new Library();

        HashMap<CompactDisc, Integer> newStock = new HashMap<>();
        newStock.put(highwayToNowhere, 10);
        newStock.put(soulManDrake, 10);
        newStock.put(soulManCody, 10);
        newStock.put(laDaDee, 10);
        library.receiveCDStock(newStock);

        HashMap<CompactDisc, Integer> searchResults = new HashMap<>();
        searchResults.put(highwayToNowhere, 10);
        searchResults.put(soulManDrake, 10);
        searchResults.put(soulManCody, 10);
        searchResults.put(laDaDee, 10);
        assertEquals(searchResults, library.searchCatalogue(null, null));
    }
}
