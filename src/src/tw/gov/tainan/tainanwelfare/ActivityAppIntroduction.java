package tw.gov.tainan.tainanwelfare;

import tw.gov.tainan.tainanwelfare.R;
import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class ActivityAppIntroduction extends Activity {
	
	private Button btnClose;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialogfragment_appintroduction);
		
		//¦Û­qActionBar
		getActionBar().setDisplayShowCustomEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setCustomView(R.layout.actionbar_custom);
		TextView tvTitle = (TextView)findViewById(R.id.CustomActionBarTitle);
		tvTitle.setText("Ãö©ó");
				
		
	}
}
