/*      PANDA -- a simple transaction monitor
                                                                                
Copyright (C) 1998-1999 Ogochan.
			  2000-2003 Ogochan & JMA (Japan Medical Association).
                                                                                
This module is part of PANDA.
                                                                                
		PANDA is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY.  No author or distributor accepts responsibility
to anyone for the consequences of using it or for whether it serves
any particular purpose or works at all, unless he says so in writing.
Refer to the GNU General Public License for full details.
                                                                                
		Everyone is granted permission to copy, modify and redistribute
PANDA, but only under the conditions described in the GNU General
Public License.  A copy of this license is supposed to have been given
to you along with PANDA so you can know your rights and
responsibilities.  It should be in a file named COPYING.  Among other
things, the copyright notice and this notice must be preserved on all
copies.
*/

package org.montsuqi.client.marshallers;

import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.montsuqi.client.Messages;
import org.montsuqi.client.Protocol;
import org.montsuqi.client.Type;
import org.montsuqi.monsia.Interface;

class ComboMarshaller extends WidgetMarshaller {

	public synchronized boolean receive(WidgetValueManager manager, Component widget) throws IOException {
		Protocol con = manager.getProtocol();
		JComboBox combo = (JComboBox)widget;

		DefaultComboBoxModel model = (DefaultComboBoxModel)combo.getModel();
		String selected = null;
		Interface xml = con.getInterface();
		con.receiveDataTypeWithCheck(Type.RECORD);

		for (int i = 0, n = con.receiveInt(), count = 0; i < n; i++) {
			String name = con.receiveString();
			if (handleStateStyle(manager, widget, name)) {
				continue;
			} else if ("count".equals(name)) { //$NON-NLS-1$
				count = con.receiveIntData();
			} else if ("item".equals(name)) { //$NON-NLS-1$
				List list = new ArrayList();
				list.add(""); //$NON-NLS-1$
				con.receiveDataTypeWithCheck(Type.ARRAY); /* Type.ARRAY */
				for (int j = 0, num = con.receiveInt(); j < num ; j++) {
					String buff = null;
					try {
						buff = con.receiveStringData();
						if (buff != null) {
							if (j < count) {
								list.add(buff);
							}
						}
					} catch (IllegalArgumentException e) {
						logger.warn(e.getMessage());
					}
				}
				model.removeAllElements();
				Iterator iter = list.iterator();
				while (iter.hasNext()) {
					model.addElement(iter.next());
				}
			} else {
				StringBuffer widgetName = con.getWidgetNameBuffer();
				int offset = widgetName.length();
				widgetName.replace(offset, widgetName.length(), '.' + name);
				Component sub =  xml.getWidgetByLongName(widgetName.toString());
				if (sub != null) {
					JTextField dummy = (JTextField)sub;
					WidgetMarshaller entryMarshaller = new EntryMarshaller();
					entryMarshaller.receive(manager, dummy);
					selected = dummy.getText();
				} else {
					logger.fatal(Messages.getString("ComboMarshaller.subwidget_not_found")); //$NON-NLS-1$
				}
			}
		}
		if (selected != null) {
			combo.setSelectedItem(selected);
		}
		return true;
	}

	public synchronized boolean send(WidgetValueManager manager, String name, Component widget) throws IOException {
		return true;
	}
}
