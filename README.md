# PlantUML going serverless
Up to now, you need either a local installation of PlantUML or a server (remote or local) to be able to generate diagrams.

Thanks for [CheerpJ](https://docs.leaningtech.com/cheerpj/), we have published a pure Javascript implementation of PlantUML.

This implementation runs within [a simple browser](https://xezzon.github.io/plantuml-core/raw.html).

The goal of this core project is to provide a very basic library, so that **other libraries could be built from it**:

  * Improved editor [PlantUML.js](https://github.com/plantuml/plantuml.js) by [Sakir Temel](https://github.com/sakirtemel)


## What is CheerpJ?

[CheerpJ](https://docs.leaningtech.com/cheerpj/) is a Java bytecode to WebAssembly and JavaScript compiler, compatible with 100% of Java, which allows to compile any Java SE application, library or Java applet into a WebAssembly/JavaScript application.

We are going to use the two following components from [CheerpJ](https://docs.leaningtech.com/cheerpj/):

  * The CheerpJ AOT compiler, an LLVM-based Java-bytecode to JavaScript compiler. This can be used to convert Java archives (e.g. `plantuml-core.jar`) files to JavaScript.
  * The CheerpJ runtime library, a full Java SE runtime in WebAssembly and JavaScript, that can be distributed in part or in full with applications converted with CheerpJ.

Note that it does not work with a local file (with double-clicking on the file). Files must be served by some webserver (even if it is a local one).

## How it works?

The plantuml-core project provides a dedicated version of PlantUML that can be compiled to Javascript with CheerpJ. The jar file `plantuml-core.jar` can be compiled to javascript with the simple command:

```
cheerpj_2.3/cheerpjfy.py --deps cheerpj_2.3/cheerpj-dom.jar plantuml-core.jar
```

You can get both files `plantuml-core.jar` and `plantuml-core.jar.js` [from Github](https://github.com/plantuml/plantuml-core/releases).

## Online demo for the impatient

All those pages are intentionally as simple as possible, so that people could quickly understand how they work and develop their own libraries:

  * [Using a raw canvas](https://xezzon.github.io/plantuml-core/raw.html), the fastest solution (same [in dark mode](https://xezzon.github.io/plantuml-core/raw-dark.html))
  * [Generating SVG](https://xezzon.github.io/plantuml-core/svg.html) (same in [dark mode](https://xezzon.github.io/plantuml-core/svg-dark.html))
  * [Generating PNG](https://xezzon.github.io/plantuml-core/png.html) (same in [dark mode](https://xezzon.github.io/plantuml-core/png-dark.html))


## Javascript API
### SVG generation

First, you have to load the runtime provided by Leaning Technology:

```
<script src="https://cjrtnc.leaningtech.com/2.3/loader.js"></script>
```

Then, you have to init PlantUML itself:

```
cheerpjRunMain("com.plantuml.api.cheerpj.v1.RunInit", "/app/plantuml-core.jar")
```

The `/app/` is a [virtual file-system that is the root of your webserver](https://docs.leaningtech.com/cheerpj/File-System-support). In the previous example, it means that both files `plantuml-core.jar` and `plantuml-core.jar.js` must be stored at the root of your server (so something like `http://127.0.0.1:8080/plantuml-core.jar.js`).

Finally, you can get the SVG version of any diagram:

```
cjCall("com.plantuml.api.cheerpj.v1.Svg", "convert", "light", text);
```

where:

  * `"light"`: could be either `dark` or `light` depending of the theme you want to use.
  * `text`: String that contains the diagram source text.

[Working demo](https://xezzon.github.io/plantuml-core/svg.html)

### PNG generation
Note that PNG generation is slower, because PNG compression takes time.

Once again, you have to load the runtime provided by Leaning Technology:

```
<script src="https://cjrtnc.leaningtech.com/2.3/loader.js"></script>
```

Then, you have to init PlantUML itself:

```
cheerpjRunMain("com.plantuml.api.cheerpj.v1.RunInit", "/app/plantuml-core.jar")
```

Finally, you can get the PNG version of any diagram:

```
cjCall("com.plantuml.api.cheerpj.v1.Png", "convertToBlob", "light", text, "/files/result.png");
```

where:

  * `"light"`: could be either `dark` or `light` depending of the theme you want to use.
  * `text`: String that contains the diagram source text.

[Working demo](https://xezzon.github.io/plantuml-core/png.html)


### Complete list of available functions

| Class | Method | Description | Arguments |  Return |
| --- | --- | --- | --- | --- |
| `Svg` |  `convert` | Generate an diagram using SVG format |  <ol><li>`mode`: either `"dark"` or `"light"`</li><li>`text`: the text source of the diagram</li></ol> | Either: <ul><li>The SVG source code generated if everything is ok</li><li>Or a JSON description of what was wrong</li></ul> 
| `Png` |  `convertToBase64` | Generate an diagram using PNG format encoded in Base64 |  <ol><li>`mode`: either `"dark"` or `"light"`</li><li>`text`: the text source of the diagram</li></ol> | Either: <ul><li>A Base64 String of a PNG imag</li><li>Or a JSON description of what was wrong</li></ul> 
| `Png` |  `convertToBlob` | Generate an diagram in PNG to a Blob |  <ol><li>`mode`: either `"dark"` or `"light"`</li><li>`text`: the text source of the diagram</li><li>`pathOut`: path of the Blob</li></ol> | A JSON description of the conversion.<br><br>The image itself is in the Blob. 
| `Raw` |  `convertToBlob` | Generate an diagram in raw (uncompressed) graphics to a Blob |  <ol><li>`mode`: either `"dark"` or `"light"`</li><li>`text`: the text source of the diagram</li><li>`pathOut`: path of the Blob</li></ol> | A JSON description of the conversion.<br><br>The image itself is in the Blob in raw format (RGBA). 
| `Info` |  `decode` | Decode a [PlantUML Text Encoding encoded String](https://plantuml.com/text-encoding). |  <ol><li>`text`: some encoded string</li></ol> | The decoded text of the diagram.
| `Info` |  `encode` | Encode some diagram text to a [PlantUML Text Encoding encoded String](https://plantuml.com/text-encoding). |  <ol><li>`text`: The text of the diagram</li></ol> | The encoded string.
| `Info` |  `syntaxCheck` | Check if a text diagram is syntaxically correct. |  <ol><li>`text`: The text source of the diagram</li></ol> | A JSON description of the syntax.

## Limitations


### Need for a web server

Even if this is a 100% Javascript application, [it does NOT run by simple opening the HTML page directly from disk](https://docs.leaningtech.com/cheerpj/Frequently-Asked-Questions#my-application-compiled-with-cheerpj-does-not-work-and-i-just-see-the-cheerpj-runtime-ready-on-the-top-of-the-screen-whats-going-on).

The URL in the browser should always start with `http://` or `https://`, if it starts with `file://` CheerpJ will not work. You need to use a local web server during testing.

If you have python installed, you can for example use in the current directory `python3 -m http.server 8080` or `python -m http.server 8080`

### Smetana limitation

This version of PlantUML depends on [Smetana, a port from C to Java of GraphViz/DOT source code](https://plantuml.com/smetana02).

Simple diagrams are working fine, however, this port is not 100% finished, so there might be some issue on complex diagrams.

### CheerpJ Licence

CheerpJ Licence is provided for non commercial use.
[See licence details here](LICENCE-CHEERPJ.md).


### Startup time

The startup time may be improved.

To take advantage of parallel downloads, and reduce download and startup time, CheerpJ allows [to pre-specify a list of resources](https://docs.leaningtech.com/cheerpj/Startup-time-optimization) (CheerpJ runtime modules) to be loaded at startup.

This feature is not used with PlantUML up to now.





