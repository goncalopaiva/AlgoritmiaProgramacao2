package edu.ufp.inf.lp2._test;

import edu.ufp.inf.lp2._project.Cache;
import edu.ufp.inf.lp2._project.Geocaching;
import edu.ufp.inf.lp2._project.TravelBug;
import edu.ufp.inf.lp2._project.User;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Hashtable;

import static org.junit.Assert.assertEquals;

class GeocachingTest {

    private final Geocaching instance;

    public GeocachingTest() throws FileNotFoundException {
        this.instance = new Geocaching();
        instance.carregarInfo("input");
    }

    @Test
    public void carregarInfo() {
        System.out.println("carregarInfo\n");
        int nUsers = 0;
        int nUsersExpected = 6;
        for (User user : instance.users.values()) {
            // System.out.println(user.toString());
            nUsers++;
        }

        int nRegioes = 0;
        int nCaches = 0;
        int nRegioesExpected = 3;
        int nCachesExpected = 18;
        for (Hashtable<String, Cache> caches : instance.regioesComCaches.values()) {
            nRegioes++;
            for (Cache cache : caches.values()) {
                // System.out.println(cache.toString());
                nCaches++;
            }
        }

        int nEdges = instance.graph.numEdges();
        int nEdgesExpected = 50;

        int nTravelBugs = 0;
        int nTravelBugsExpected = 3;
        for (TravelBug travelBug : instance.travelBugs.values()) {
            // System.out.println(travelBug.toString());
            nTravelBugs++;
        }

        assertEquals(nUsersExpected, nUsers);
        assertEquals(nRegioesExpected, nRegioes);
        assertEquals(nCachesExpected, nCaches);
        assertEquals(nEdgesExpected, nEdges);
        assertEquals(nTravelBugsExpected, nTravelBugs);
    }
}