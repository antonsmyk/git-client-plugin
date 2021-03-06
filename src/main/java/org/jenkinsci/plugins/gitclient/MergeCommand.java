package org.jenkinsci.plugins.gitclient;

import org.eclipse.jgit.lib.ObjectId;

/**
 * MergeCommand interface.
 *
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public interface MergeCommand extends GitCommand {

    /**
     * Sets the first revision to include in the merge.  If additional revisions
     * need to be added to the merge, use addRevisionToMerge.
     *
     * @param rev a {@link org.eclipse.jgit.lib.ObjectId} object.
     * @return a {@link org.jenkinsci.plugins.gitclient.MergeCommand} object.
     * @see #addRevisionToMerge
     */
    MergeCommand setRevisionToMerge(ObjectId rev);

    /**
     * Add to the list of revisions to merge.  Most interesting when performing
     * a merge of multiple branches simultaneously ("octopus" merge).  
     * Use setRevisionToMerge for the first revision to merge.
     *
     * @param rev a {@link org.eclipse.jgit.lib.ObjectId} object.
     * @return a {@link org.jenkinsci.plugins.gitclient.MergeCommand} object.
     * @see #setRevisionToMerge
     */
    MergeCommand addRevisionToMerge(ObjectId rev);

    /**
     * setMessage.
     *
     * @param message the desired comment for the merge command.
     * @return a {@link org.jenkinsci.plugins.gitclient.MergeCommand} object.
     */
    MergeCommand setMessage(String message);

    /**
     * setStrategy.
     *
     * @param strategy a {@link org.jenkinsci.plugins.gitclient.MergeCommand.Strategy} object.
     * @return a {@link org.jenkinsci.plugins.gitclient.MergeCommand} object.
     */
    MergeCommand setStrategy(Strategy strategy);

    public enum Strategy {
        DEFAULT, RESOLVE, RECURSIVE, OCTOPUS, OURS, SUBTREE;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    /**
     * Select the fast forward mode.
     * The name FastForwardMode collides with org.eclipse.jgit.api.MergeCommand.FastForwardMode
     * so we have to choose a different name.
     *
     * @param fastForwardMode mode to be used in this merge
     * @return MergeCommand to be used in fluent calls
     */
    MergeCommand setGitPluginFastForwardMode(GitPluginFastForwardMode fastForwardMode);

    public enum GitPluginFastForwardMode {
        FF,        // Default option, fast forward update the branch pointer only
        FF_ONLY,   // Create a merge commit even for a fast forward
        NO_FF;     // Abort unless the merge is a fast forward

        @Override
        public String toString() {
            return "--"+name().toLowerCase().replace("_","-");
        }
    }

    /**
     * setSquash
     *
     * @param squash - whether to squash commits or not
     * @return a {@link org.jenkinsci.plugins.gitclient.MergeCommand} object.
     */
    MergeCommand setSquash(boolean squash);

    /**
     * setCommit
     *
     * @param commit - whether or not to commit the result after a successful merge.
     * @return a {@link org.jenkinsci.plugins.gitclient.MergeCommand} object.
     */
    MergeCommand setCommit(boolean commit);
}
