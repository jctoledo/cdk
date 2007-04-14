
/*
 *  $RCSfile$
 *  $Author: Sushil Ronghe $
 *  $Date: 2007-04-12  $
 *  $Revision: 6631 $
 *
 *  Copyright (C) 2002-2006  The Chemistry Development Kit (CDK) project
 *
 *  Contact: cdk-devel@lists.sourceforge.net
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2.1
 *  of the License, or (at your option) any later version.
 *  All I ask is that proper credit is given for my work, which includes
 *  - but is not limited to - adding the above copyright notice to the beginning
 *  of your source code files, and to any copyright notice that you may distribute
 *  with programs based on this work.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 */
package org.openscience.cdk.isomorphism.matchers.smarts;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.CDKConstants;

/**
 * This matcher checks the number of ring connections of the checked Atom
 * with other Atom's. This cannot be matched with a unpreprocessed Atom!
 *
 * @cdk.module extra
 */

public class TotalRingConnectionAtom extends SMARTSAtom {
    private int Connection_Size;
    /** Creates a new instance of TotalRingConnectionAtom */
    public TotalRingConnectionAtom(int m_Connection_Size){
        Connection_Size = m_Connection_Size;
    }
    public boolean matches(IAtom atom) {
      if(atom.getProperty(CDKConstants.RING_CONNECTIONS)!=null){
       Integer total_ring_bond = ((Integer)atom.getProperty(CDKConstants.RING_CONNECTIONS)).intValue();
      if(Connection_Size == total_ring_bond)
          return true;
      }
      return false;
    }
    public String toString() {
        return ("Total Ring Connection Atom of size "+ Connection_Size);
    }
    
    
}
