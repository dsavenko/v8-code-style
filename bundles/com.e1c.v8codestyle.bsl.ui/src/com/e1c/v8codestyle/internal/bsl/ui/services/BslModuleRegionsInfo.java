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

import com._1c.g5.v8.dt.bsl.common.IBslModuleTextInsertInfo;
import com._1c.g5.v8.dt.bsl.model.Module;

/**
 * Built-in language module region information with {@link String} region wrap data
 *
 * @author Kuznetsov Nikita
 */
public class BslModuleRegionsInfo
    implements IBslModuleTextInsertInfo
{
    private final int insertPosition;
    private final Module module;
    private final String regionName;

    /**
     * {@link BslModuleRegionsInfo} constructor
     *
     * @param insertPosition <code>int</code> insertion offset, cannot be negative
     * @param module current {@link Module}, cannot be <code>null</code>
     * @param regionName {@link String} region name, can be <code>null</code>
     */
    public BslModuleRegionsInfo(int insertPosition, Module module, String regionName)
    {
        this.insertPosition = insertPosition;
        this.module = module;
        this.regionName = regionName;
    }

    @Override
    public int getInsertPosition()
    {
        return insertPosition;
    }

    @Override
    public Module getModule()
    {
        return module;
    }

    /**
     * Returns region name
     *
     * @return {@link String} region name, can be <code>null</code>
     */
    public String getRegionName()
    {
        return regionName;
    }
}
