// Generated code from Butter Knife. Do not modify!
package com.xiaohei.auser.wenliapp.normalActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaohei.auser.wenliapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingActivity_ViewBinding implements Unbinder {
  private SettingActivity target;

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target, View source) {
    this.target = target;

    target.img_return = Utils.findRequiredViewAsType(source, R.id.img_return, "field 'img_return'", ImageView.class);
    target.tv_return = Utils.findRequiredViewAsType(source, R.id.tv_return, "field 'tv_return'", TextView.class);
    target.listView = Utils.findRequiredViewAsType(source, R.id.setlist, "field 'listView'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.img_return = null;
    target.tv_return = null;
    target.listView = null;
  }
}
