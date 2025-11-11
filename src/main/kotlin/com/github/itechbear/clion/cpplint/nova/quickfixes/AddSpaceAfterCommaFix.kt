package com.github.itechbear.clion.cpplint.nova.quickfixes

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project

/**
 * Quick fix to add space after comma.
 *
 * This fix addresses `whitespace/comma` violations by ensuring all commas
 * are followed by a space (except at end of line).
 *
 * Examples:
 * - foo(a,b,c) → foo(a, b, c)
 * - {1,2,3} → {1, 2, 3}
 */
class AddSpaceAfterCommaFix : LocalQuickFix {

    override fun getFamilyName(): String = "Add space after comma"

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

        // Add space after commas that are not followed by whitespace
        // Pattern: ,([^\s]) → , $1
        val fixedLine = lineText.replace(Regex(",([^\\s])"), ", $1")

        // Only modify if there's actually a change
        if (fixedLine != lineText) {
            document.replaceString(lineStartOffset, lineEndOffset, fixedLine)
            FileDocumentManager.getInstance().saveDocument(document)
        }
    }
}
