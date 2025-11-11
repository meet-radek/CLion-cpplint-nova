# CLion Cpplint Nova

<!-- Plugin description -->
A cpplint plugin for JetBrains CLion 2025.2+ with support for CLion Nova. This plugin automatically runs cpplint against your C++ files while you write code, showing warnings directly in your editor.

**Based on the original work by [Hu Dong (itechbear)](https://github.com/itechbear/CLion-cpplint)** – completely rewritten in Kotlin for CLion Nova compatibility.
<!-- Plugin description end -->

## Screenshots

![Settings](/screenshots/Settings.PNG)

![Lint](/screenshots/Lint.PNG)

## Features

- **Real-time code style checking** - Run cpplint.py on the fly when editing C++ source code
- **Inline highlighting** - Corresponding lines highlighted with cpplint.py warnings
- **Quick fixes** - Automatic fixes for common violations (header guards, ending newlines)
- **CLion Nova compatible** - Works with both CLion Nova and CLion Classic
- **Modern architecture** - Built with IntelliJ Platform Gradle Plugin 2.x and Kotlin
- **Cygwin/MinGW support** - Works in different Windows environments

## Installation

### From JetBrains Marketplace

1. Go to **File → Settings → Plugins**
2. Search for "CLion Cpplint Nova"
3. Click **Install**
4. Restart CLion

### From Disk (Manual)

1. Download the latest `CLion-cpplint-nova-X.X.X.zip` from [Releases](https://github.com/meet-radek/CLion-cpplint-nova/releases)
2. Go to **File → Settings → Plugins**
3. Click **⚙️** → **Install Plugin from Disk...**
4. Select the downloaded ZIP file
5. Restart CLion

## Usage

1. **Install Python and cpplint**
   ```bash
   pip install cpplint
   ```

2. **Configure the plugin**
   - Go to **File → Settings → Cpplint**
   - Fill in the **absolute paths**:
     - **Python path**: Path to your Python executable
     - **Cpplint path**: Path to cpplint executable or script
     - **Cpplint options**: (Optional) Additional command-line options

3. **Start coding!**
   - The plugin automatically runs when you open/edit C++ files
   - No menus or actions needed – it works in the background
   - Warnings appear inline in your editor

## Configuration Notes

### For Cygwin Users
- Use the Cygwin python package
- Fill with unix-style paths in the options dialog
  - Example: `/usr/bin/python` and `/home/tools/cpplint.py`

### For MinGW Users
- Use Windows-style paths
  - Example: `C:\Python\python.exe` and `C:\Users\user\cpplint.py`

### For Regular Windows/Linux/macOS Users
- **Python path**: Where Python is installed
  - Windows: `C:\Python312\python.exe`
  - Linux/Mac: `/usr/bin/python3` or find with `which python3`
- **Cpplint path**: Where cpplint is installed
  - After `pip install cpplint`, use:
    - Windows: `C:\Python312\Scripts\cpplint.exe`
    - Linux/Mac: `/usr/local/bin/cpplint` or find with `which cpplint`

## Requirements

- CLion 2025.2 or later
- Python (2.7 or 3.x)
- cpplint (`pip install cpplint`)

## Change Log

### Version 1.0.0 - Nova Edition
- Complete rewrite in Kotlin for CLion 2025.2 Nova compatibility
- Based on original work by Hu Dong (itechbear)
- Modern IntelliJ Platform Gradle Plugin 2.x architecture
- Works with both CLion Nova and CLion Classic
- Quick fixes for ending newline and header guards
- Configurable Python and cpplint paths via Settings

### Previous Versions (Original Plugin by Hu Dong)
- **2019.3** - Compatible with CLion 2019.3
- **2019.1.1** - Fix issue #27 (Can't find highlight display level: WEAK_WARNING)
- **2019.1** - Compatible with 2019.1
- **1.1.0** - Compatible with 2018.3
- **1.0.9** - Compatible with 2018.2
- Earlier versions... (see [original repository](https://github.com/itechbear/CLion-cpplint))

## Building from Source

See [BUILD.md](BUILD.md) for detailed build instructions.

## Contributing

Contributions are welcome! Please feel free to submit issues or pull requests.

## Credits

- **Original Author**: [Hu Dong (itechbear)](https://github.com/itechbear)
- **Nova Port**: [Brad Chamberlain (meet-radek)](https://github.com/meet-radek)
- **cpplint**: [Google's C++ Style Guide](https://google.github.io/styleguide/cppguide.html)

## License

This project inherits the license from the original CLion-cpplint plugin by Hu Dong.

## Links

- **Original Plugin**: https://github.com/itechbear/CLion-cpplint
- **cpplint**: https://github.com/cpplint/cpplint
- **Google C++ Style Guide**: https://google.github.io/styleguide/cppguide.html
- **JetBrains Marketplace**: (coming soon)
