# CLion Cpplint Nova Changelog

## [Unreleased]

## [1.1.0] - 2025-11-11

### Added
- Created FEATURES.md with potential future enhancements
- NOLINT suppression quick fix - Add `// NOLINT(category)` comments to suppress specific cpplint warnings
- Support for multiple quick fixes per violation (specific fix + NOLINT suppression)
- Remove trailing whitespace quick fix - Automatically trim trailing spaces/tabs from lines with `whitespace/end_of_line` violations
- Replace tabs with spaces quick fix - Convert tab characters to spaces (2 spaces per tab) for `whitespace/tab` violations
- Add space after comma quick fix - Automatically add spaces after commas for `whitespace/comma` violations (e.g., `foo(a,b)` → `foo(a, b)`)
- Add space after comment marker quick fix - Automatically add space after `//` for `whitespace/comments` violations (e.g., `//TODO` → `// TODO`)
- Add space after semicolon quick fix - Automatically add spaces after semicolons in for loops for `whitespace/semicolon` violations
- Add endif comment quick fix - Automatically add guard name comment to `#endif` for `build/endif_comment` violations
- Test Configuration button in settings - Verify Python and cpplint installation with `--version` check
- Auto-Detect Paths button - Automatically find Python and cpplint installations on Windows, Linux, and macOS
- Improved auto-detection to prioritize matching Python + cpplint pairs from the same installation
- Enable/Disable inspection toggle - Simple checkbox in settings to enable or disable cpplint inspection entirely
- Severity level mapping - High-confidence violations (5) shown as errors (red), medium confidence (3-4) as warnings (yellow), low confidence (1-2) as weak warnings (gray)

### Changed
- Header guard quick fix now parses and uses cpplint's expected guard name from error message instead of generating its own
- Header guard quick fix always updates #endif comment to match current guard name
- Updated README.md with correct JetBrains Marketplace URL (plugin ID 28989)
- Updated .gitignore to exclude .kotlin/ directory

## [1.0.2] - 2025-11-10

### Changed
- Updated plugin ID from com.github.meet-radek to com.github.meet-brad-ch
- Updated repository URL to https://github.com/meet-brad-ch/CLion-cpplint-nova
- Updated GitHub username references throughout documentation

## [1.0.1] - 2025-11-10

### Fixed
- Removed "CLion" from plugin name to comply with JetBrains Marketplace requirements
- Fixed deprecated API usage: replaced FileChooserDescriptorFactory with FileChooserDescriptor constructor
- Fixed deprecated API usage: replaced Project.baseDir with Project.basePath

## [1.0.0] - 2025-11-10

### Added
- Complete rewrite in Kotlin for CLion 2025.2 Nova compatibility
- Modern IntelliJ Platform Gradle Plugin 2.x architecture
- Works with both CLion Nova and CLion Classic
- Quick fixes for ending newline and header guards
- Configurable Python and cpplint paths via Settings

### Changed
- Based on original work by Hu Dong (itechbear)
- Migrated from Java to Kotlin
- Updated to modern plugin development practices
