/*
 * Copyright (C) 2016  Irotsoma, LLC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.irotsoma.cloudbackenc.cloudbackencui

import java.util.*

/**
 * Created by justin on 7/29/2016.
 *
 * Global properties object for the application.properties file
 */

//TODO: now that UI app is using spring, use that to load properties.
val applicationProperties = Properties().apply {
     load (ClassLoader.getSystemClassLoader().getResourceAsStream("application.properties"))
}