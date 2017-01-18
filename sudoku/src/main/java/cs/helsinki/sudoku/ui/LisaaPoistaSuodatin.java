package cs.helsinki.sudoku.ui;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class LisaaPoistaSuodatin extends DocumentFilter {

    private Kayttoliittyma ui;
    private int rivi;
    private int sarake;
    private JTextField kentta;

    public LisaaPoistaSuodatin(Kayttoliittyma ui, JTextField kentta, int rivi, int sarake) {
        this.ui = ui;
        this.kentta = kentta;
        this.rivi = rivi;
        this.sarake = sarake;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string,
            AttributeSet attr) throws BadLocationException {

        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.insert(offset, string);
        String syote = sb.toString();
        if (validoiSyote(syote)) {
            super.insertString(fb, offset, string, attr);
            kasitteleValidiSyote(syote);

        } else {
            // varoita käyttäjää
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text,
            AttributeSet attrs) throws BadLocationException {

        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.replace(offset, offset + length, text);
        String syote = sb.toString();

        if (validoiSyote(syote)) {
            super.replace(fb, offset, length, text, attrs);
            kasitteleValidiSyote(syote);
        } else {
            // varoita käyttäjää
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length)
            throws BadLocationException {
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.delete(offset, offset + length);
        String syote = sb.toString();

        if (validoiSyote(syote)) {
            super.remove(fb, offset, length);
            kasitteleValidiSyote("0");
        } else {
            // varoita käyttäjää
        }
    }

    private boolean validoiSyote(String syote) {
        if (syote.length() == 0) {
            return true;
        };

        try {
            int luku = Integer.parseInt(syote);
            if (luku < 1 || luku > 9) {
                return false;
            };
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void kasitteleValidiSyote(String syote) {
        int luku = Integer.parseInt(syote);
        ui.paivitaArvo(luku, rivi, sarake);
    }

}
