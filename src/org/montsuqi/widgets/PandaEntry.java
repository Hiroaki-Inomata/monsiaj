package org.montsuqi.widgets;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class PandaEntry extends JTextField {
	public PandaEntry(String text, int columns) {
		super(new PandaDocument(), text, columns);
	}
	public PandaEntry(int columns) {
		this(null, columns);
	}
	public PandaEntry(String text) {
		this(text, 0);
	}
	public PandaEntry() {
		this(null, 0);
	}	

	public void setInputMode(int mode) {
		PandaDocument doc = (PandaDocument)getDocument();
		doc.setInputMode(mode);
	}

	public int getInputMode() {
		PandaDocument doc = (PandaDocument)getDocument();
		return doc.getInputMode();
	}

	public static void main(String[] args) {
		final JFrame f = new JFrame("TestPandaEntry");
		PandaEntry pe = new PandaEntry();
		pe.setInputMode(PandaDocument.KANA);
		System.out.println(pe.getInputMode());
		f.getContentPane().setLayout(new BorderLayout());
		f.getContentPane().add(pe, BorderLayout.CENTER);
		f.setSize(200, 50);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.show();
	}
}

class PandaDocument extends PlainDocument {
	public static final int KANA = 1;
	public static final int XIM = 2;
	public static final int ASCII = 3;

	protected int mode;

	static final char[] SYMBOL_TABLE = {
		'��', '��', '��', '��', '��', '��', '��', '��',
		'��', '��', '��', '��', '��', '��', '��', '��',
		'��', '��', '��', '��', '��', '��', '��', '��',
		'��', '��', '��', '��', '��', '��', '��', '��',
		'��', '��', '��', '��', '��', '��', '��', '��',
		'��', '��', '��', '��', '��', '��', '��', '��',
		'��', '��', '��', '��', '��', '��', '��', '��',
		'��', '��', '��', '��', '��', '��', '��', '��',
		'��', '��',    0,    0,    0, '��',    0,    0,
		   0, '��',    0,    0,    0,    0,    0, '��',
		   0,    0,    0,    0,    0, '��',    0,    0,
		   0,    0,    0, '��', '��', '��', '��',    0
	};

	static final RuleEntry[] KANA_TABLE = {
		new RuleEntry("l",  "��", "��", "��", "��", "��"),
		new RuleEntry("x",  "��", "��", "��", "��", "��"),
		new RuleEntry("k",  "��", "��", "��", "��", "��"),
		new RuleEntry("ky", "����", "����", "����", "����", "����"),
		new RuleEntry("g",  "��", "��", "��", "��", "��"),
		new RuleEntry("gy", "����", "����", "����", "����", "����"),
		new RuleEntry("s",  "��", "��", "��", "��", "��"),
		new RuleEntry("sh", "����", "��", "����", "����", "����"),
		new RuleEntry("sy", "����", "����", "����", "����", "����"),
		new RuleEntry("z",  "��", "��", "��", "��", "��"),
		new RuleEntry("j",  "����", "��", "����", "����", "����"),
		new RuleEntry("jy", "����", "����", "����", "����", "����"),
		new RuleEntry("zy", "����", "����", "����", "����", "����"),
		new RuleEntry("t",  "��", "��", "��", "��", "��"),
		new RuleEntry("ts", null, null, "��", null, null),
		new RuleEntry("lt", null, null, "��", null, null),
		new RuleEntry("xt", null, null, "��", null, null),
		new RuleEntry("ty", "����", "����", "����", "����", "����"),
		new RuleEntry("cy", "����", "����", "����", "����", "����"),
		new RuleEntry("ch", "����", "��", "����", "����", "����"),
		new RuleEntry("th", "�ƥ�", "�ƥ�", "�ƥ�", "�ƥ�", "�ƥ�"),
		new RuleEntry("d",  "��", "��", "��", "��", "��"),
		new RuleEntry("dy", "�¥�", "�¥�", "�¥�", "�¥�", "�¥�"),
		new RuleEntry("dh", "�ǥ�", "�ǥ�", "�ǥ�", "�ǥ�", "�ǥ�"),
		new RuleEntry("dw", "�ɥ�", "�ɥ�", "�ɥ�", "�ɥ�", "�ɥ�"),
		new RuleEntry("n",  "��", "��", "��", "��", "��"),
		new RuleEntry("ny", "�˥�", "�˥�", "�˥�", "�˥�", "�˥�"),
		new RuleEntry("h",  "��", "��", "��", "��", "��"),
		new RuleEntry("f",  "�ե�", "�ե�", "��", "�ե�", "�ե�"),
		new RuleEntry("hy", "�ҥ�", "�ҥ�", "�ҥ�", "�ҥ�", "�ҥ�"),
		new RuleEntry("fy", "�ե�", "�ե�", "�ե�", "�ե�", "�ե�"),
		new RuleEntry("b",  "��", "��", "��", "��", "��"),
		new RuleEntry("by", "�ӥ�", "�ӥ�", "�ӥ�", "�ӥ�", "�ӥ�"),
		new RuleEntry("p",  "��", "��", "��", "��", "��"),
		new RuleEntry("py", "�ԥ�", "�ԥ�", "�ԥ�", "�ԥ�", "�ԥ�"),
		new RuleEntry("m",  "��", "��", "��", "��", "��"),
		new RuleEntry("my", "�ߥ�", "�ߥ�", "�ߥ�", "�ߥ�", "�ߥ�"),
		new RuleEntry("y",  "��", "��", "��", "����", "��"),
		new RuleEntry("ly", "��", null, "��", null, "��"),
		new RuleEntry("xy", "��", null, "��", null, "��"),
		new RuleEntry("r",  "��", "��", "��", "��", "��"),
		new RuleEntry("ry", "���", "�ꥣ", "���", "�ꥧ", "���"),
		new RuleEntry("w",  "��", "��", "��", "��", "��"),
		new RuleEntry("wh", "��", "����", "��", "����", "����"),
		new RuleEntry("lw", "��", null, null, null, null),
		new RuleEntry("xw", "��", null, null, null, null),
		new RuleEntry("v",  "����", "����", "��", "����", "����"),
		new RuleEntry("q",  "����", "����", "��", "����", "����"),
	};

	protected static final String LOW_ALPHABETS = "abcdefghijklmnopqrstuvwxyz";
	protected static final String AEIOU = "aeiou";

	PandaDocument() {
		mode = ASCII;
	}

	public void setInputMode(int mode) {
		if (mode != KANA && mode != XIM && mode != ASCII) {
			throw new IllegalArgumentException();
		}
		this.mode = mode;
	}

	public int getInputMode(){
		return mode;
	}

	public void insertString(int offs, String str, AttributeSet a)
		throws BadLocationException {

		// Handle kana input
		if (mode == KANA) {
			// Do nothing if there is no input
			if (str.length() < 1) {
				return;
			}

			// Just insert for non-keyboard input
			if (str.length() > 1) {
				super.insertString(offs, str, a);
				return;
			}

			if (str.length() != 1) {
				throw new IllegalStateException("cannot hapen");
			}

			// Find the prefix
			int prefixStart;
			int prefixEnd = offs;

			for (prefixStart = prefixEnd; prefixStart > 0; prefixStart--) {
				String s = getText(prefixStart - 1, 1);
				if (LOW_ALPHABETS.indexOf(s.charAt(0)) < 0) {
					break;
				}
			}

			String prefix = getText(prefixStart, prefixEnd - prefixStart);
			char thisChar = str.charAt(0);
			if (prefix.length() == 0) {
				// single char
				char c = getSymbol(thisChar);
				if (c != 0) {
					str = String.valueOf(c);
				} else {
					super.insertString(offs, str, a);
					return;
				}
			} else if (AEIOU.indexOf(thisChar) >= 0) {
				// ����
				String s = getKana(prefix, thisChar);
				if (s != null) {
					str = s;
				} else {
					super.insertString(offs, str, a);
					return;
				}
			} else if (prefix.charAt(0) == 'n' && thisChar != 'y') {
				// n -> ��
				str = "��";
				if (thisChar != 'n' && thisChar != '\'') {
					str += thisChar;
				}
			} else if (thisChar == prefix.charAt(0)) {
				// xx -> ��
				str = "��" + thisChar;
			} else {
				super.insertString(offs, str, a);
				return;
			}

			super.remove(prefixStart, prefixEnd - prefixStart);
			offs = prefixStart;
		}
		super.insertString(offs, str, a);
	}

	private static char getSymbol(int key) {
		return 0x20 <= key && key <= 0x80 ? SYMBOL_TABLE[key - 0x20] : 0;
	}

	private static String getKana(String prefix, char key) {
		for (int i = 0; i < KANA_TABLE.length; i++) {
			if (prefix.equals(KANA_TABLE[i].prefix)) {
				switch (key) {
				case 'a': return KANA_TABLE[i].a;
				case 'i': return KANA_TABLE[i].i;
				case 'u': return KANA_TABLE[i].u;
				case 'e': return KANA_TABLE[i].e;
				case 'o': return KANA_TABLE[i].o;
				default:  return null;
				}
			}
		}
		return null;
	}
}

class RuleEntry {
	final String prefix;
	final String a, i, u, e, o;

	RuleEntry(String prefix, String a, String i, String u, String e, String o) {
		this.prefix = prefix;
		this.a = a;
		this.i = i;
		this.u = u;
		this.e = e;
		this.o = o;
	}
}

