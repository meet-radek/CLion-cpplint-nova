package com.github.itechbear.clion.cpplint.nova.quickfixes

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project

/**
 * Quick fix to add comment to #endif for header guards.
 *
 * This fix addresses `build/endif_comment` violations by adding a comment
 * to #endif directives that indicates the corresponding guard name.
 *
 * Examples:
 * - #endif → #endif  // FOO_BAR_H_
 * - #endif → #endif  // MYCLASS_H_
 *
 * The fix searches the beginning of the file for #ifndef or #define directives
 * to extract the guard name.
 */
class AddEndifCommentFix : LocalQuickFix {

    override fun getFamilyName(): String = "Add endif comment"

    override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
        val document = QuickFixesManager.getDocument(project, descriptor) ?: return

        // Get the text range of the problem
        val textRange = descriptor.textRangeInElement ?: descriptor.psiElement.textRange ?: return

        // Get the line where the problem occurs
        val problemOffset = textRange.startOffset
        val lineNumber = document.getLineNumber(problemOffset)
        val lineStartOffset = document.getLineStartOffset(lineNumber)
        val lineEndOffset = document.getLineEndOffset(lineNumber)

        // Get the line text
        val lineText = document.getText(com.intellij.openapi.util.TextRange(lineStartOffset, lineEndOffset))

        // Only process if this line contains #endif
        if (!lineText.trimStart().startsWith("#endif")) {
            return
        }

        // Search the beginning of the file for header guard name
        val guardName = extractHeaderGuardName(document) ?: return

        // Add comment to #endif if it doesn't already have one
        val trimmedLine = lineText.trimEnd()
        val fixedLine = if (trimmedLine.endsWith("#endif")) {
            "$trimmedLine  // $guardName"
        } else {
            // #endif already has something after it, don't modify
            trimmedLine
        }

        // Only modify if there's actually a change
        if (fixedLine != lineText) {
            document.replaceString(lineStartOffset, lineEndOffset, fixedLine)
            FileDocumentManager.getInstance().saveDocument(document)
        }
    }

    /**
     * Extract the header guard name from the beginning of the file.
     * Looks for #ifndef GUARD_NAME or #define GUARD_NAME patterns.
     */
    private fun extractHeaderGuardName(document: com.intellij.openapi.editor.Document): String? {
        val lineCount = document.lineCount
        val maxLinesToSearch = minOf(50, lineCount) // Search first 50 lines

        for (i in 0 until maxLinesToSearch) {
            val lineStartOffset = document.getLineStartOffset(i)
            val lineEndOffset = document.getLineEndOffset(i)
            val line = document.getText(com.intellij.openapi.util.TextRange(lineStartOffset, lineEndOffset))
            val trimmedLine = line.trim()

            // Look for #ifndef GUARD_NAME
            if (trimmedLine.startsWith("#ifndef")) {
                val parts = trimmedLine.split(Regex("\\s+"))
                if (parts.size >= 2) {
                    return parts[1]
                }
            }

            // Look for #define GUARD_NAME (usually follows #ifndef)
            if (trimmedLine.startsWith("#define")) {
                val parts = trimmedLine.split(Regex("\\s+"))
                if (parts.size >= 2) {
                    return parts[1]
                }
            }
        }

        return null
    }
}
