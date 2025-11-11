package com.github.itechbear.clion.cpplint.nova.quickfixes

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project

/**
 * Quick fix to suppress cpplint warnings by adding NOLINT comment.
 *
 * This adds a comment like `// NOLINT(category)` at the end of the line
 * with the violation, which tells cpplint to ignore that specific rule.
 *
 * @param category The cpplint category to suppress (e.g., "whitespace/tab")
 */
class SuppressWithNoLintFix(private val category: String) : LocalQuickFix {

    override fun getFamilyName(): String = "Suppress with NOLINT"

    override fun getName(): String = "Suppress with NOLINT($category)"

    override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
        val document = QuickFixesManager.getDocument(project, descriptor) ?: return

        // Get the text range of the problem (this is the range within the file)
        val textRange = descriptor.textRangeInElement ?: descriptor.psiElement.textRange ?: return

        // Get the line where the problem occurs
        val problemOffset = textRange.startOffset
        val lineNumber = document.getLineNumber(problemOffset)
        val lineEndOffset = document.getLineEndOffset(lineNumber)

        // Check if NOLINT comment already exists on this line
        val lineStartOffset = document.getLineStartOffset(lineNumber)
        val lineText = document.getText(com.intellij.openapi.util.TextRange(lineStartOffset, lineEndOffset))

        if (lineText.contains("NOLINT")) {
            // Line already has a NOLINT comment, don't add another
            return
        }

        // Add NOLINT comment at end of line
        val nolintComment = "  // NOLINT($category)"
        document.insertString(lineEndOffset, nolintComment)
        FileDocumentManager.getInstance().saveDocument(document)
    }
}
