/*
 * otr4j, the open source java otr library.
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package net.java.otr4j;

import java.security.KeyPair;

import net.java.otr4j.session.FragmenterInstructions;
import net.java.otr4j.session.InstanceTag;
import net.java.otr4j.session.SessionID;

/**
 * 
 * This interface should be implemented by the host application. It is required
 * for otr4j to work properly.
 * 
 * @author George Politis
 * 
 */
public abstract interface OtrEngineHost {
	public abstract void injectMessage(SessionID sessionID, String msg)
			throws OtrException;

	public abstract void unreadableMessageReceived(SessionID sessionID)
			throws OtrException;

	public abstract void unencryptedMessageReceived(SessionID sessionID,
			String msg) throws OtrException;

	public abstract void showError(SessionID sessionID, String error)
			throws OtrException;

	public abstract void smpError(SessionID sessionID, int tlvType,
			boolean cheated) throws OtrException;

	public abstract void smpAborted(SessionID sessionID) throws OtrException;

	public abstract void finishedSessionMessage(SessionID sessionID,
			String msgText) throws OtrException;

	public abstract void requireEncryptedMessage(SessionID sessionID,
			String msgText) throws OtrException;

	public abstract OtrPolicy getSessionPolicy(SessionID sessionID);
	
	/**
	 * Get instructions for the necessary fragmentation operations.
	 *
	 * If no fragmentation is necessary, return <tt>null</tt> to set the default
	 * fragmentation instructions which are to use an unlimited number of
	 * messages of unlimited size each. Hence fragmentation is not necessary or
	 * applied.
	 *
	 * @param sessionID
	 *            the session ID of the session
	 * @return return fragmentation instructions or null for defaults (i.e. no
	 *         fragmentation)
	 */
	public abstract FragmenterInstructions getFragmenterInstructions(SessionID sessionID); 

	public abstract KeyPair getLocalKeyPair(SessionID sessionID)
			throws OtrException;

	public abstract byte[] getLocalFingerprintRaw(SessionID sessionID);

	public abstract void askForSecret(SessionID sessionID, InstanceTag receiverTag, String question);

	public abstract void verify(SessionID sessionID, String fingerprint, boolean approved);

	public abstract void unverify(SessionID sessionID, String fingerprint);

	public abstract String getReplyForUnreadableMessage(SessionID sessionID);

	public abstract String getFallbackMessage(SessionID sessionID);

	public abstract void messageFromAnotherInstanceReceived(SessionID sessionID);

	public abstract void multipleInstancesDetected(SessionID sessionID);
}
