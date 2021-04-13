/*
GanttProject is an opensource project management tool.
Copyright (C) 2005-2011 GanttProject team

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package net.sourceforge.ganttproject.importer;

import java.util.List;

import net.sourceforge.ganttproject.GanttOptions;
import net.sourceforge.ganttproject.IGanttProject;
import net.sourceforge.ganttproject.gui.UIFacade;
import net.sourceforge.ganttproject.language.GanttLanguage;
import net.sourceforge.ganttproject.plugins.PluginManager;
import net.sourceforge.ganttproject.wizard.AbstractWizard;

/**
 * @author bard
 */
public class ImportFileWizardImpl extends AbstractWizard {
  private static List<Importer> ourImporters = getImporters();

  private static GanttLanguage i18n = GanttLanguage.getInstance();

  public ImportFileWizardImpl(UIFacade uiFacade, IGanttProject project, GanttOptions options) {
    super(uiFacade, i18n.getText("importWizard.dialog.title"),
      new ImporterChooserPage(ourImporters, uiFacade, options.getPluginPreferences().node("/instance/net.sourceforge.ganttproject/import")));
    for (Importer importer : ourImporters) {
      importer.setContext(project, uiFacade, options.getPluginPreferences());
    }
  }

  private static List<Importer> getImporters() {
    return PluginManager.getExtensions(Importer.EXTENSION_POINT_ID, Importer.class);
  }
}
