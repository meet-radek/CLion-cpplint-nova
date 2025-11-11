package com.github.itechbear.clion.cpplint.nova.quickfixes

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project

/**
 * Quick fix to remove trailing whitespace from a line.
 *
 * This fix addresses `whitespace/end_of_line` violations by trimming
 * any trailing spaces or tabs from the end of the line.
 */
class RemoveTrailingWhitespaceFix : LocalQuickFix {

    override fun getFamilyName(): String = "Remove trailing whitespace"

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

        // Calculate the trimmed line (remove trailing whitespace)
        val trimmedLine = lineText.trimEnd()

        // Only modify if there's actually trailing whitespace
        if (trimmedLine.length < lineText.length) {
            // Replace the line with the trimmed version
            document.replaceString(lineStartOffset, lineEndOffset, trimmedLine)
            FileDocumentManager.getInstance().saveDocument(document)
        }
    }
}
