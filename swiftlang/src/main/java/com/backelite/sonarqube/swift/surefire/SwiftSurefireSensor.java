/**
 * Swift SonarQube Plugin - Swift module - Enables analysis of Swift and Objective-C projects into SonarQube.
 * Copyright © 2015 Backelite (${email})
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.backelite.sonarqube.swift.surefire;

import com.backelite.sonarqube.commons.surefire.BaseSurefireSensor;
import com.backelite.sonarqube.swift.lang.core.Swift;
import org.apache.commons.lang.StringUtils;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.resources.Project;
import org.sonar.api.scan.filesystem.PathResolver;

import java.io.File;

public final class SwiftSurefireSensor extends BaseSurefireSensor {


    public SwiftSurefireSensor(FileSystem fileSystem, PathResolver pathResolver, ResourcePerspectives resourcePerspectives, Settings settings) {
        super(fileSystem, pathResolver, resourcePerspectives, settings);
    }

    @Override
    public boolean shouldExecuteOnProject(Project project) {
        return StringUtils.isNotEmpty(this.reportPath()) && fileSystem.languages().contains(Swift.KEY);
    }

    @Override
    protected void collect(SensorContext context, File reportsDir) {
        LOGGER.info("parsing {}", reportsDir);
        new SwiftSurefireParser(fileSystem, resourcePerspectives, context).collect(reportsDir);
    }

    @Override
    public String toString() {
        return "Swift Surefire Sensor";
    }

}
