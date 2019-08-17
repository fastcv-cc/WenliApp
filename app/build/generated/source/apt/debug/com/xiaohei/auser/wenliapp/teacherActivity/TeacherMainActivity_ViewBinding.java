// Generated code from Butter Knife. Do not modify!
package com.xiaohei.auser.wenliapp.teacherActivity;

import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaohei.auser.wenliapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TeacherMainActivity_ViewBinding implements Unbinder {
  private TeacherMainActivity target;

  @UiThread
  public TeacherMainActivity_ViewBinding(TeacherMainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TeacherMainActivity_ViewBinding(TeacherMainActivity target, View source) {
    this.target = target;

    target.list_select = Utils.findRequiredViewAsType(source, R.id.list_select, "field 'list_select'", ListView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.drawer = Utils.findRequiredViewAsType(source, R.id.drawer_layout, "field 'drawer'", DrawerLayout.class);
    target.radioGroup = Utils.findRequiredViewAsType(source, R.id.group_tea, "field 'radioGroup'", RadioGroup.class);
    target.rd_manage = Utils.findRequiredViewAsType(source, R.id.rd_manage, "field 'rd_manage'", RadioButton.class);
    target.rd_diaryread = Utils.findRequiredViewAsType(source, R.id.rd_diaryread, "field 'rd_diaryread'", RadioButton.class);
    target.text_name = Utils.findRequiredViewAsType(source, R.id.teacher_name, "field 'text_name'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TeacherMainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.list_select = null;
    target.toolbar = null;
    target.drawer = null;
    target.radioGroup = null;
    target.rd_manage = null;
    target.rd_diaryread = null;
    target.text_name = null;
  }
}
