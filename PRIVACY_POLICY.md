# Privacy Policy - APK-SFTP

**Last Updated**: March 2026

## Overview
APK-SFTP is an Android SSH/SFTP client that enables secure file transfer between your device and remote servers. We take your privacy seriously.

## Data Collection & Usage

### What We Collect
- **SSH Connection Details**: Hostname, port, username (never stored in plain text)
- **SSH Private Keys**: Stored encrypted in Android Keystore (never leave the device)
- **Connection Profiles**: Saved locally on your device

### What We Don't Collect
- We do NOT collect your personal data
- We do NOT send data to external servers (except SSH servers you connect to)
- We do NOT track your activity
- We do NOT use analytics
- We do NOT require accounts or logins to our service

### Data Encryption
- All SSH keys are encrypted using Android Keystore
- All connection profiles are encrypted using EncryptedSharedPreferences
- All data remains on your device

## Permissions

The app requests the following permissions:

- **INTERNET**: For SSH/SFTP connections
- **READ/WRITE_EXTERNAL_STORAGE**: To access files on your device
- **ACCESS_NETWORK_STATE**: To check network connectivity
- **FOREGROUND_SERVICE**: For background file transfers (optional)

## Data Retention

All data is stored locally on your device. You can delete the app data at any time through Android Settings.

## Third-Party Libraries

The app uses:
- JSch for SSH/SFTP protocol
- AndroidX Security for encryption
- Jetpack Compose for UI

These libraries have their own privacy policies.

## Security

- SSH connections use industry-standard encryption
- Private keys are never exposed in memory
- Session timeouts for automatic disconnection after inactivity

## Changes to This Policy

We may update this policy. Check back periodically for updates.

## Contact

For questions about privacy, contact the developer.

---

**Your security and privacy matter to us. All sensitive data is encrypted and never leaves your device.**
