# Cpplint Nova

<!-- Plugin description -->
A cpplint plugin for JetBrains CLion 2025.2+ with support for CLion Nova. This plugin automatically runs cpplint against your C++ files while you write code, showing warnings directly in your editor.

**Based on the original work by [Hu Dong (itechbear)](https://github.com/itechbear/CLion-cpplint)** – completely rewritten in Kotlin for CLion Nova compatibility.
<!-- Plugin description end -->

[![JetBrains Plugins](https://img.shields.io/jetbrains/plugin/v/28989-cpplint-nova.svg)](https://plugins.jetbrains.com/plugin/28989-cpplint-nova)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/28989-cpplint-nova.svg)](https://plugins.jetbrains.com/plugin/28989-cpplint-nova)

## About

This is a Nova-compatible port of the [original CLion-cpplint plugin](https://github.com/itechbear/CLion-cpplint) by Hu Dong. For detailed usage instructions and configuration examples, please refer to the [original plugin documentation](https://github.com/itechbear/CLion-cpplint#readme).

## Key Features

- ✅ **CLion Nova compatible** - Works with both CLion Nova and CLion Classic
- ✅ **Real-time code style checking** - Run cpplint on the fly when editing C++ source code
- ✅ **Inline highlighting** - Warnings appear directly in your editor with severity-based colors
- ✅ **9 Quick fixes** - Automatic fixes for common violations:
  - Header guards (uses cpplint's expected name)
  - Ending newlines
  - Trailing whitespace
  - Tabs to spaces
  - Space after comma
  - Space after comment marker (`//`)
  - Space after semicolon (in for loops)
  - Endif comments
  - NOLINT suppression
- ✅ **Severity mapping** - Color-coded by confidence: errors (red), warnings (yellow), weak warnings (gray)
- ✅ **Enable/Disable toggle** - Easily turn inspection on/off in settings
- ✅ **Auto-detection** - Automatically find Python and cpplint installations (prioritizes matching pairs)
- ✅ **Configuration testing** - Test button in settings to verify Python and cpplint installation
- ✅ **Modern architecture** - Built with IntelliJ Platform Gradle Plugin 2.x and Kotlin

## Quick Start

1. **Install from JetBrains Marketplace**
   - Go to **File → Settings → Plugins**
   - Search for "Cpplint Nova"
   - Click **Install**

2. **Install cpplint**
   ```bash
   pip install cpplint
   ```

   *Alternatively, download [cpplint.py](https://raw.githubusercontent.com/cpplint/cpplint/develop/cpplint.py) directly from the [cpplint repository](https://github.com/cpplint/cpplint).*

3. **Configure**
   - Go to **File → Settings → Cpplint**
   - Set paths to Python and cpplint executables

For detailed configuration instructions (Cygwin, MinGW, path examples), see the [original plugin documentation](https://github.com/itechbear/CLion-cpplint#configuration-notes).

## Screenshots

![Settings](/screenshots/Settings.PNG)
![Lint](/screenshots/Lint.PNG)

## Requirements

- CLion 2025.2 or later
- Python (2.7 or 3.x)
- cpplint (`pip install cpplint`)

## Building from Source

See [BUILD.md](BUILD.md) for detailed build instructions.

## Contributing

Contributions are welcome! Please feel free to submit issues or pull requests.

## Credits

- **Original Author**: [Hu Dong (itechbear)](https://github.com/itechbear)
- **Nova Port**: [Brad Chamberlain (meet-brad-ch)](https://github.com/meet-brad-ch)
- **cpplint**: [Google's C++ Style Guide](https://google.github.io/styleguide/cppguide.html)

## License

This project inherits the license from the original CLion-cpplint plugin by Hu Dong.

## Links

- **JetBrains Marketplace**: https://plugins.jetbrains.com/plugin/28989-cpplint-nova
- **Original Plugin**: https://github.com/itechbear/CLion-cpplint (by Hu Dong)
- **cpplint**: https://github.com/cpplint/cpplint
- **Google C++ Style Guide**: https://google.github.io/styleguide/cppguide.html
