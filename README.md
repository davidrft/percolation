# Percolation
Assignment from week 1 of [Princeton's Algorithms](https://www.coursera.org/learn/algorithms-part1) Course on Coursera.

We model a percolation system using an n-by-n grid of sites. Each site is either open or blocked. A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites. We say the system percolates if there is a full site in the bottom row. In other words, a system percolates if we fill all open sites connected to the top row and that process fills some open site on the bottom row. (For the insulating/metallic materials example, the open sites correspond to metallic materials, so that a system that percolates has a metallic path from top to bottom, with full sites conducting. For the porous substance example, the open sites correspond to empty space through which water might flow, so that a system that percolates lets water fill open sites, flowing from top to bottom.)


### Built With

The library [alg4.jar](http://algs4.cs.princeton.edu/code/algs4.jar) from the course was used.

