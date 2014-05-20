# boost

Yet another collection of Clojure utilty functions. Just one 'core' namespace collecting general purpose functions aggregated here for quick searching and re-use. No specific relation with the Boost library of C++ fame other than having the similar intent to completement the standard lib. Except a few `(credited)` exceptions, all snippets come from projects I worked on, sometimes inspired by other snippets, articles, discussions.

## Install

Put this dependency in your project.clj:

    :dependencies [
                   [boost "0.1.2"]
                  ]

And then:

    (ns your-prj-here.core
      (:require 
                [boost.core :as boost]
                ))

## Provided utils

* optimal partitioning of coll into parts of the same size
* expanding coll to a bigger size using a filler element
* interleave all, including collections of different sizes
* index of the first occurence of an item in coll
* swapping elements in a sequence
* map-val: updating all values in a map using a custom function
* update-all: updating values for the specified keys in a map using a custom function
* file system utils

## Internals documentation

Tests are pretty comprehensive (boost was developed test first). You can run them with:

    lein midje

and they give you a detailed overview at what each function is doing. Functions are almost always documented in code.

## License

Copyright © 2014 Renzo Borgatti

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
