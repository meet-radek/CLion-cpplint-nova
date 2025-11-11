package com.github.itechbear.clion.cpplint.nova.inspection

import com.github.itechbear.clion.cpplint.nova.runner.CpplintRunner
import com.intellij.codeInspection.InspectionManager
import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile

/**
 * Main inspection entry point for cpplint.
 *
 * This inspection runs cpplint on C/C++ files and reports violations
 * as problem descriptors with optional quick fixes.
 */
class CpplintInspection : LocalInspectionTool() {

    override fun checkFile(
        file: PsiFile,
        manager: InspectionManager,
        isOnTheFly: Boolean
    ): Array<ProblemDescriptor>? {
        // Only check C/C++ files
        if (!CpplintLanguageType.isCFamily(file)) {
            return null
        }

        // Get document for the file
        val document = PsiDocumentManager.getInstance(file.project)
            .getDocument(file) ?: return null

        // Run cpplint and get problem descriptors
        val problems = CpplintRunner.lint(file, manager, document)

        return if (problems.isEmpty()) null else problems.toTypedArray()
    }
}
