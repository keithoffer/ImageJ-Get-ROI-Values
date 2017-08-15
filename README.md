Get ROI Values 1.0.0
====================

A simple plugin for ImageJ 1.x to export the value and (x,y) pixel co-ordinates for all pixels within a ROI (8-bit greyscale only).

There might be a way to export this data from within ImageJ itself without plugins, but I couldn't find it and used this as an excuse to play around with ImageJ's plugin API.

Installation
-----

Drop Get_ROI_Values.class in your plugins subfolder of your ImageJ installation.

Usage
-----

Select a region of interest, choose the plugin from the plugins menu (It will be labeled 'Get ROI Values'). The pixel values and locations will be presented in a new results window. If no ROI is chosen, the plugin will analyse the whole image.

License
-------

Get ROI Values is copyrighted free software made available under the terms of the Expat License

Copyright: (C) 2017 by Keith Offer. All Rights Reserved.
