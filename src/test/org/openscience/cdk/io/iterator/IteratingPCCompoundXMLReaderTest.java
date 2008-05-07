/* $Revision$ $Author$ $Date$
 *
 * Copyright (C) 2008  Egon Willighagen <egonw@users.sf.net>
 * 
 * Contact: cdk-devel@slists.sourceforge.net
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * All we ask is that proper credit is given for our work, which includes
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
 *  */
package org.openscience.cdk.io.iterator;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.openscience.cdk.CDKTestCase;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.interfaces.IMolecule;
import org.openscience.cdk.interfaces.IMoleculeSet;
import org.openscience.cdk.tools.LoggingTool;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @cdk.module test-io
 */
public class IteratingPCCompoundXMLReaderTest extends CDKTestCase {

    private LoggingTool logger;

    public IteratingPCCompoundXMLReaderTest(String name) {
        super(name);
        logger = new LoggingTool(this);
    }

    public static Test suite() {
        return new TestSuite(IteratingPCCompoundXMLReaderTest.class);
    }

    public void testList() throws Exception {
        String filename = "data/asn/pubchem/aceticAcids38.xml";
        logger.info("Testing: " + filename);
        InputStream ins = this.getClass().getClassLoader().getResourceAsStream(filename);
        IteratingPCCompoundXMLReader reader = new IteratingPCCompoundXMLReader(
        	new InputStreamReader(ins),
        	DefaultChemObjectBuilder.getInstance()
        );

        int molCount = 0;
        IMoleculeSet set = DefaultChemObjectBuilder.getInstance().newMoleculeSet();
        while (reader.hasNext()) {
//        	System.out.println("next molecule found");
            Object object = reader.next();
            assertNotNull(object);
            assertTrue(object instanceof IMolecule);
            set.addMolecule((IMolecule)object);
            molCount++;
        }

        assertEquals(3, molCount);
        IMolecule first = set.getMolecule(0);
        assertEquals(8, first.getAtomCount());
        assertEquals(7, first.getBondCount());
    }

}
