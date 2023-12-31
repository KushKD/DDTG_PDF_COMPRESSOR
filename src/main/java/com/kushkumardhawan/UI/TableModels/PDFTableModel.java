package com.kushkumardhawan.UI.TableModels;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.kushkumardhawan.Core.PDFFile;

/**
 *
 * @author Kush Dhawan
 */
public class PDFTableModel extends AbstractTableModel
{

    private final List<PDFFile> fItems;
    private final int fColumnsCount = 3;

    public PDFTableModel()
    {
        fItems = new ArrayList<>();
    }

    public PDFTableModel(List<PDFFile> items)
    {
        this.fItems = items;
    }

    public PDFFile getPDFFileFromRow(int row)
    {
        return fItems.get(row);
    }

    @Override
    public int getRowCount()
    {
        return fItems.size();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return Object.class;
    }

    public void add(PDFFile item)
    {
        fItems.add(item);
        final int row = fItems.indexOf(item);
        fireTableRowsInserted(row, row);
    }

    public void remove(PDFFile item)
    {
        if (fItems.contains(item))
        {
            final int row = fItems.indexOf(item);
            fItems.remove(row);
            fireTableRowsDeleted(row, row);
        }
    }

    @Override
    public int getColumnCount()
    {
        return fColumnsCount;
    }

    @Override
    public String getColumnName(int column)
    {
        switch (column)
        {
            case 0:
                return "Filename";
            case 1:
                return "Size (Kb)";
            case 2:
                return "Status";

        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        PDFFile item = fItems.get(rowIndex);

        switch (columnIndex)
        {
            case 0:
                return item.getFileName();

            case 1:
                return String.valueOf(item.getSizeKb());

            case 2:

                if (item.getCompressedSize() > 0)
                {
                    return "Result: " + String.valueOf(item.getCompressedSize()) + "Kb";
                }
                else
                {
                    if (item.getMessage() != null)
                    {
                        return item.getMessage();
                    }
                    else
                    {

                    }
                }
        }
        return null;
    }

}
