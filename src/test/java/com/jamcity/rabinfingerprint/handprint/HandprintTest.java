package com.jamcity.rabinfingerprint.handprint;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import junit.framework.TestCase;

import com.jamcity.rabinfingerprint.polynomial.Polynomial;

public class HandprintTest extends TestCase {
	public void testChunkingFiles() throws IOException {
		Polynomial p = Polynomial.createIrreducible(53);
		
		List<InputStream> sims = TestDataGenerator.getSimilarRandomBytes(4);
		Handprints.HandPrintFactory factory = Handprints.newFactory(p);
		Handprint hand1 = factory.newHandprint(sims.get(0));
		Handprint hand2 = factory.newHandprint(sims.get(1));
		Handprint hand3 = factory.newHandprint(sims.get(2));
		Handprint hand4 = factory.newHandprint(sims.get(3));

		assertTrue(Math.abs(0.70 - hand1.getSimilarity(hand2)) < 0.05);
		assertTrue(Math.abs(0.70 - hand1.getSimilarity(hand3)) < 0.05);
		assertTrue(Math.abs(0.70 - hand1.getSimilarity(hand4)) < 0.05);
		
		List<InputStream> diffs = TestDataGenerator.getDifferentRandomBytes(3);
		Handprint hand5 = factory.newHandprint(diffs.get(0));
		Handprint hand6 = factory.newHandprint(diffs.get(1));
		Handprint hand7 = factory.newHandprint(diffs.get(2));

		assertTrue(Math.abs(0.00 - hand1.getSimilarity(hand5)) < 0.05);
		assertTrue(Math.abs(0.00 - hand1.getSimilarity(hand6)) < 0.05);
		assertTrue(Math.abs(0.00 - hand1.getSimilarity(hand7)) < 0.05);
	}
}
