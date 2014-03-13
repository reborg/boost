# boost

Yet another collection of Clojure utilty functions. Just one 'core' namespace collecting general purpose functions aggregated here for quick searching and re-use. No specific relation with the Boost library of C++ fame! Except a few (credited) exceptions, all snippets come from other projects of mine.

## Install

Put this dependency in your project.clj

    :dependencies [[...]
                   [boost "0.1.2"]
                   [...]]

And then:

    (ns yourprjhere.core
      (:require [...]
                [boost.core :as boost]
                [...]))

## Provided utils

* optimal partitioning of coll into parts of the same size
* expanding coll to a bigger size using a filler element
* interleave all, including collections of different sizes
* index of the first occurence of an item in coll
* swapping elements in a sequence
* updating values in a map
* file system utils

## Internals documentation

Tests are pretty comprehensive (boost was developed test first). You can run them with:

    lein midje

and they give you a detailed overview at what each function is doing. Functions are almost always documented in code.

## License

Copyright © 2014 Renzo Borgatti

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
