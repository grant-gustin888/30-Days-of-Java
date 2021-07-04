package SoftwareTesting.GatheringRequirements;

import SoftwareTesting.TestDrivenDevelopment.GatheringRequirements.CompactDisc;
import SoftwareTesting.TestDrivenDevelopment.GatheringRequirements.InsufficientStockException;
import SoftwareTesting.TestDrivenDevelopment.GatheringRequirements.Library;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuyCDTest {

    @Test
    public void cdIsInStock() throws InsufficientStockException {
        CompactDisc highwayToNowhere = new CompactDisc("Highway to Nowhere", "Drake Bell");
        Library library = new Library();

        HashMap<CompactDisc, Integer> newStock = new HashMap<>();
        newStock.put(highwayToNowhere, 10);
        library.receiveCDStock(newStock);

        library.buy(highwayToNowhere, 1);
        assertEquals(9, library.getStock(highwayToNowhere));
    }

    @Test
    public void insufficientStock() {
        CompactDisc highwayToNowhere = new CompactDisc("Highway to Nowhere", "Drake Bell");
        Library library = new Library();

        HashMap<CompactDisc, Integer> newStock = new HashMap<>();
        newStock.put(highwayToNowhere, 1);
        library.receiveCDStock(newStock);

        assertThrows(InsufficientStockException.class, () -> library.buy(highwayToNowhere, 2));
    }
}
