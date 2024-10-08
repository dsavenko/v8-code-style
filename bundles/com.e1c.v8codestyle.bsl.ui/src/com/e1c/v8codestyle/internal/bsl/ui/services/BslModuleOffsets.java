/*******************************************************************************
 * Copyright (C) 2023, 1C-Soft LLC and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     1C-Soft LLC - initial API and implementation
 *******************************************************************************/
package com.e1c.v8codestyle.internal.bsl.ui.services;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.xtext.nodemodel.INode;

import com.e1c.v8codestyle.internal.bsl.ui.UiPlugin;

/**
 * Provides offsets and suffixes information of module region
 *
 * @author Kuznetsov Nikita
 */
public final class BslModuleOffsets
{
    private int startOffset;
    private int endOffset;
    private int insertOffset;

    private boolean needReplace;

    private Map<String, BslModuleOffsets> suffixes;

    /**
     * Create and calculate module region offsets
     *
     * @param document actual {@link IDocument} to get offsets from, can't be {@code null}
     * @param node {@link INode} to get offsets from, can't be {@code null}
     * @param nodeAfter {@link INode} to get offsets from, can't be {@code null}
     * @return module region information with calculated region offsets or {@code null}
     */
    public static BslModuleOffsets create(IDocument document, INode node, INode nodeAfter)
    {
        try
        {
            int startLine = node.getTotalStartLine() - 1;
            int startOffset = document.getLineOffset((startLine <= 1) ? 0 : startLine);
            int endOffset = nodeAfter.getTotalOffset();
            int insertOffset = document.getLineOffset(document.getLineOfOffset(nodeAfter.getTotalOffset()));
            return new BslModuleOffsets(startOffset, endOffset, insertOffset);
        }
        catch (BadLocationException ex)
        {
            UiPlugin.log(UiPlugin.createErrorStatus("Can't create module region information", ex)); //$NON-NLS-1$
        }
        return null;
    }

    /**
     * Returns start offset of module region
     *
     * @return offset before region declaration
     */
    public int getStartOffset()
    {
        return startOffset;
    }

    /**
     * Returns end offset of module region
     *
     * @return offset after region declaration
     */
    public int getEndOffset()
    {
        return endOffset;
    }

    /**
     * Returns before end offset inside module region
     *
     * @return offset before end of region declaration
     */
    public int getBeforeEndOffset()
    {
        return insertOffset;
    }

    /**
     * Is need to replace existing module region
     *
     * @return <code>true</code> if need to replace existing region, <code>false</code> otherwise
     */
    public boolean needReplace()
    {
        return needReplace;
    }

    /**
     * Sets necessity of replacing existing module region
     */
    public void setNeedReplace()
    {
        needReplace = true;
    }

    /**
     * Add suffix of the name of this module region
     *
     * @param suffix to add to suffixes map of this module region
     * @param document to update offsets, can't be {@code null}
     * @param node to update offsets, can't be {@code null}
     * @param nodeAfter to update offsets, can't be {@code null}
     */
    public void addSuffix(String suffix, IDocument document, INode node, INode nodeAfter)
    {
        if (suffixes == null)
        {
            suffixes = new HashMap<>();
        }
        BslModuleOffsets suffixRegionInformation = create(document, node, nodeAfter);
        if (suffixRegionInformation != null)
        {
            suffixes.put(suffix, suffixRegionInformation);
            int suffixStartOffset = suffixRegionInformation.getStartOffset();
            int suffixEndOffset = suffixRegionInformation.getEndOffset();
            int suffixInsertOffset = suffixRegionInformation.getBeforeEndOffset();
            if (insertOffset < suffixInsertOffset)
            {
                insertOffset = suffixInsertOffset;
                endOffset = suffixEndOffset;
            }
            if (startOffset > suffixStartOffset)
            {
                startOffset = suffixStartOffset;
            }
        }
    }

    /**
     * Is module region has suffixes
     *
     * @return {@code true} if module region has suffixes, {@code false} otherwise
     */
    public boolean hasSuffixes()
    {
        return suffixes != null;
    }

    /**
     * Returns module region information by suffix if exists
     *
     * @param suffix {@link String} suffix of declared name module region
     * @return {@link BslModuleOffsets} if suffix exists, {@code null} otherwise
     */
    public BslModuleOffsets getInformationBySuffix(String suffix)
    {
        if (hasSuffixes())
        {
            return suffixes.get(suffix);
        }
        return null;
    }

    private BslModuleOffsets(int startOffset, int endOffset, int insertOffset)
    {
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.insertOffset = insertOffset;
    }
}
