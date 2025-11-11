package com.github.itechbear.clion.cpplint.nova.quickfixes

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project

/**
 * Quick fix to replace tabs with spaces.
 *
 * This fix addresses `whitespace/tab` violations by replacing all tab
 * characters with spaces. Uses 2 spaces per tab (Google C++ Style Guide).
 */
class ReplaceTabsWithSpacesFix : LocalQuickFix {

    companion object {
        // Google C++ Style Guide uses 2 spaces for indentation
        private const val SPACES_PER_TAB = 2
    }

    override fun getFamilyName(): String = "Replace tabs with spaces"

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

        // Replace all tabs with spaces
        if (lineText.contains('\t')) {
            val spacedLine = lineText.replace("\t", " ".repeat(SPACES_PER_TAB))
            document.replaceString(lineStartOffset, lineEndOffset, spacedLine)
            FileDocumentManager.getInstance().saveDocument(document)
        }
    }
}
