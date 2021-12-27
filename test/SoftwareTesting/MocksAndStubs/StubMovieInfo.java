package SoftwareTesting.MocksAndStubs;

import SoftwareTesting.TestDrivenDevelopment.Mocks.MovieInfo;

import java.util.HashMap;
import java.util.Map;

class StubMovieInfo implements MovieInfo {

    private final String title;
    private final int year;

    StubMovieInfo(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public Map<String, String> fetch(String imbdID) {
        Map<String, String> info = new HashMap<>();
        info.put("title", title);
        info.put("year", Integer.toString(year));
        return info;
    }
}
