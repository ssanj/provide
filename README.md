# Provide

If you need a quick way to serve any directory as a website then Provide will make your life easier.

## Goals

1. Quickly serve a directory as a website.

## Usage

Simply run Provide in the directory of your choosing with:

```
provide
```

This will launch Provide with its default options:

1. netty server
1. port 8080
1. current directory

It will print out the following output:

```
 __   __   __          __   ___
|__) |__) /  \ \  / | |  \ |__
|    |  \ \__/  \/  | |__/ |___

server: netty
host  : localhost
port  : 8080
root  : /Users/sanj/Downloads/swagger-ui-master/dist

add an index.html to the root dir to customise
```

Each of the above defaults can be changed via a supplied option.

Use the help menu to see all the options:

```
provide -h
```

which yields:

```
 __   __   __          __   ___
|__) |__) /  \ \  / | |  \ |__
|    |  \ \__/  \/  | |__/ |___

Provide version: 0.0.1
Usage: Provide [options]

  -h, --help
  -v, --version
  -s, --<server> <value>   The server type to use. Possible values are (netty|jetty). Defaults to netty
  -p, --<port> <value>     The port to run Provide on. Defaults to 8080
  -l, --<location> <value>
                           The directory from which to run Provide on.Defaults the current directory

Please see https://github.com/ssanj/provide for more examples.
```

If the directory served has an index.html file, it will be served when browsing the root path:

```
http://localhost:8080/
```

If there isn't an index.html file in the root directory then Provide will display a simple welcome page:

![Default Index Page](https://github.com/ssanj/provide/blob/master/index.jpg)

## Installation

_Provide requires a Java 8 or later JRE_.

### Install via a homebrew

Add the Provide homebrew tap:

```
brew tab ssanj/homebrew-provide
```

Install Provide:

```
brew install provide
```

### Install via direct download

Download the [executable](https://github.com/ssanj/provide/releases/download/v0.0.1/provide) from the latest Github release.

Then run it via:

```
chmod +x provide
./provide -h
```

### Build from source

Clone this repository:

```
git clone https://github.com/ssanj/provide
```

Then from within the repository run:

```
sbt assembly
```

This will generate the __provide__ executable in the __target/scala-2.12__ directory.