# GitHub Pages Build Scripts

This directory contains the scripts and configuration files used to build and deploy the Swagger UI documentation to GitHub Pages.

To quickly test locally, you first need to build the customized Swagger UI by
running the following commands:

```bash
cd .github/gh-pages
./build.sh
```

Once the build is complete, you can serve the generated files using a simple
HTTP server. Run the following command:

```bash
cd build/dist
python3 -m http.server
```

Then open `http://localhost:8000` in your browser to see the Swagger UI
documentation.
