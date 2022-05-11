package org.lj92458.plugins.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiFile
import com.intellij.psi.codeStyle.CodeStyleManager

class ReformatMine : AnAction("ReformatMine") {

    override fun actionPerformed(event: AnActionEvent) {
        val psiFile: PsiFile? = event.getData(LangDataKeys.PSI_FILE)
        val editor: Editor? = event.getData(LangDataKeys.EDITOR)
        if (psiFile != null && editor != null) {
            try {
                WriteCommandAction.runWriteCommandAction(
                    psiFile.getProject(), "ReformatMine", "empty",
                    {
                        if (editor.selectionModel.hasSelection()) {
                            CodeStyleManager.getInstance(psiFile.getProject()).reformatText(
                                psiFile,
                                editor.selectionModel.selectionStart,
                                editor.selectionModel.selectionEnd
                            )
                        } else {
                            Messages.showMessageDialog(
                                psiFile.getProject(),
                                "You must select an area, then you can reformat this area.",
                                "Message",
                                Messages.getInformationIcon()
                            )
                        }
                    },
                    psiFile
                )

            } catch (e: Exception) {
                println("reformat code failed")
            }
        } else {
            println("psiFile is null")
        }
    }
}