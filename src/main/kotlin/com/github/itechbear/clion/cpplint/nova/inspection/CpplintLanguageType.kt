package com.github.itechbear.clion.cpplint.nova.inspection

import com.intellij.psi.PsiFile

/**
 * Determines if a file is a C/C++ family file.
 *
 * TODO: Update for CLion Nova 2025.2 proper C++ file detection
 * Currently using file extension fallback.
 *
 * OLD 2019.3 API (for reference):
 * if (!(file instanceof OCFileImpl)) return false;
 * OCLanguageKind ocLanguageKind = ((OCFileImpl) file).getOCLanguageKind();
 * return ocLanguageKind.isCpp();
 */
object CpplintLanguageType {

    private val cppExtensions = setOf(
        "c", "cc", "cpp", "cxx", "c++",
        "h", "hh", "hpp", "hxx", "h++"
    )

    /**
     * Checks if the given file is a C/C++ family file.
     *
     * @param file The PSI file to check
     * @return true if the file is a C/C++ file
     */
    fun isCFamily(file: PsiFile): Boolean {
        // Fallback implementation using file extension
        val extension = file.virtualFile?.extension?.lowercase() ?: return false
        return extension in cppExtensions

        // TODO: Replace with proper Nova API when available:
        // - Research com.jetbrains.cidr.lang.* package for Nova
        // - Look for OCFile or equivalent in CLion 2025.2
        // - Check if there's a Language or FileType based detection
        //
        // Possible Nova approaches to investigate:
        // 1. file.language?.id == "ObjectiveC"
        // 2. file.fileType is OCFileType (if available)
        // 3. Check for cidr-specific PSI markers
    }
}
