/* $Revision$ $Author$ $Date$    
 * 
 * Copyright (C) 1997-2007  Egon Willighagen <egonw@users.sf.net>
 * 
 * Contact: cdk-devel@lists.sourceforge.net
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * All I ask is that proper credit is given for my work, which includes
 * - but is not limited to - adding the above copyright notice to the beginning
 * of your source code files, and to any copyright notice that you may distribute
 * with programs based on this work.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA. 
 */
package org.openscience.cdk.fingerprint;

import java.util.BitSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.openscience.cdk.CDKTestCase;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.templates.MoleculeFactory;

/**
 * @cdk.module test-standard
 */
public class FingerprinterToolTest extends CDKTestCase
{		
	
	public FingerprinterToolTest()
	{
		super();
	}

	
	@Test
	public void testIsSubset_BitSet_BitSet() throws java.lang.Exception
	{
		Fingerprinter fingerprinter = new Fingerprinter();
		
		IAtomContainer mol = MoleculeFactory.makeIndole();
		BitSet bs = fingerprinter.getBitFingerprint(mol).asBitSet();
		IAtomContainer frag1 = MoleculeFactory.makePyrrole();
		BitSet bs1 = fingerprinter.getBitFingerprint(frag1).asBitSet();
		Assert.assertTrue(FingerprinterTool.isSubset(bs, bs1));
	}

    @Test
    public void testListDifferences_BitSet_BitSet() throws Exception{
        BitSet bs1 = new BitSet();
        BitSet bs2 = new BitSet();

        bs1.set(0);   bs2.set(0);
        bs1.set(1);
        bs1.set(2);   bs2.set(2);
        bs1.set(3);
                      bs2.set(4);

        // 2 bits set in bs1 which are clear in bs2
        Assert.assertEquals(2,
                            FingerprinterTool.listDifferences(bs2, bs1).size());
        // 2 bits set in bs2 which are clear in bs1
        Assert.assertEquals(1,
                            FingerprinterTool.listDifferences(bs1, bs2).size());
	}

    @Test public void testDifferences() throws Exception {
        BitSet bs1 = new BitSet();
        BitSet bs2 = new BitSet();

        bs1.set(0);   bs2.set(0);
        bs1.set(1);
        bs1.set(2);   bs2.set(2);
        bs1.set(3);
                      bs2.set(4);

        Assert.assertEquals(3,
                            FingerprinterTool.differences(bs1, bs2).size());
    }
}

