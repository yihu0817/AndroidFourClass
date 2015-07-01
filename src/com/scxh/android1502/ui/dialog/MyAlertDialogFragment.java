package com.scxh.android1502.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;
/**
 * 调用方法:	
 * MyAlertDialogFragment dialogFragment = new MyAlertDialogFragment();
 * dialogFragment.show(getFragmentManager(), "dialog");
 *
 */
public class MyAlertDialogFragment extends DialogFragment {

	// 法一
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		return new AlertDialog.Builder(getActivity())
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("提示框")
				.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								Toast.makeText(getActivity(), "确定",
										Toast.LENGTH_SHORT).show();
							}
						})
				.setNegativeButton(android.R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								Toast.makeText(getActivity(), "取消",
										Toast.LENGTH_SHORT).show();
							}
						}).create();
	}
}