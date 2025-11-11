package com.github.itechbear.clion.cpplint.nova.quickfixes

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.io.FileUtil
import java.io.File

/**
 * Quick fix to add header guard to C++ header files.
 *
 * @param expectedGuardName The guard name suggested by cpplint (if available).
 *                          If null, generates guard name from project-relative path.
 */
class AddHeaderGuardFix(private val expectedGuardName: String? = null) : LocalQuickFix {

    override fun getFamilyName(): String = "Add header guard"

    override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
        val document = QuickFixesManager.getDocument(project, descriptor) ?: return

        // Use cpplint's expected guard name if provided
        val guard = if (expectedGuardName != null) {
            expectedGuardName
        } else {
            // Fallback: Generate guard name from relative path
            val filepath = descriptor.psiElement.containingFile.virtualFile.canonicalPath ?: return
            val root = project.basePath ?: return
            val relativePath = FileUtil.getRelativePath(File(root), File(filepath)) ?: return
            // Generate guard name from relative path: path/to/file.h -> PATH_TO_FILE_H_
            relativePath.replace(Regex("[^\\w]"), "_").uppercase() + "_"
        }

        insertGuard(document, guard)
        FileDocumentManager.getInstance().saveDocument(document)
    }

    private fun insertGuard(document: Document, guard: String) {
        // Check if header guard already exists at the beginning
        val text = document.text
        val ifndefPattern = Regex("^\\s*#ifndef\\s+(\\w+)")
        val definePattern = Regex("^\\s*#define\\s+(\\w+)")

        val lines = text.lines()
        var hasIfndef = false
        var hasDefine = false
        var ifndefLine = -1
        var defineLine = -1

        // Search first ~10 non-empty lines for existing guard
        for ((index, line) in lines.take(10).withIndex()) {
            val trimmed = line.trim()
            if (trimmed.isEmpty() || trimmed.startsWith("//")) continue

            if (!hasIfndef && ifndefPattern.matches(trimmed)) {
                hasIfndef = true
                ifndefLine = index
            } else if (!hasDefine && definePattern.matches(trimmed)) {
                hasDefine = true
                defineLine = index
            }

            // If we found both, stop searching
            if (hasIfndef && hasDefine) break
        }

        if (hasIfndef && hasDefine && ifndefLine >= 0 && defineLine >= 0) {
            // Guard exists, replace it with correct format
            val ifndefStart = document.getLineStartOffset(ifndefLine)
            val ifndefEnd = document.getLineEndOffset(ifndefLine)
            document.replaceString(ifndefStart, ifndefEnd, "#ifndef $guard")

            val defineStart = document.getLineStartOffset(defineLine)
            val defineEnd = document.getLineEndOffset(defineLine)
            document.replaceString(defineStart, defineEnd, "#define $guard")

            // Check if #endif at the end needs comment
            ensureEndifComment(document, guard)
        } else {
            // No guard exists, insert new one
            val headGuard = "#ifndef $guard\n#define $guard\n"
            document.insertString(0, headGuard)

            val tailGuard = "\n#endif  // $guard\n"
            document.insertString(document.textLength, tailGuard)
        }
    }

    private fun ensureEndifComment(document: Document, guard: String) {
        val text = document.text
        val lines = text.lines()

        // Find last #endif
        for (i in lines.size - 1 downTo maxOf(0, lines.size - 20)) {
            val line = lines[i].trim()
            if (line.startsWith("#endif")) {
                // Always update the comment to match the current guard name
                val lineStart = document.getLineStartOffset(i)
                val lineEnd = document.getLineEndOffset(i)
                document.replaceString(lineStart, lineEnd, "#endif  // $guard")
                break
            }
        }
    }
}
